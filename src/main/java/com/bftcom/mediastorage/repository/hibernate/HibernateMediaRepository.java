package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.MediaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateMediaRepository extends HibernateCrudRepository<Media> implements MediaRepository {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Media> findByParameters(@NonNull MediaSearchParameters parameters) {
        boolean byTags = parameters.getTagIds() != null && !parameters.getTagIds().isEmpty();

        ParametersSearcher searcher = this.new ParametersSearcher();

        searcher.selectFrom();

        if (byTags) {
            searcher.addStatement("JOIN m.tags t");
        }

        searcher.where();

        if (parameters.getCategoryId() != null) {
            List<Long> categoryIds = new ArrayList<>();

            Category category = categoryRepository.findById(parameters.getCategoryId());
            if (category != null) {
                collectCategoryAndSubcategoryIds(category, categoryIds);
                searcher.and().addStatement("category.id IN (:categoryIds)", Collections.singletonMap("categoryIds", categoryIds));
            }

            searcher.and().addStatement("category.id IN (:categoryIds)", Collections.singletonMap("categoryIds", categoryIds));
        }

        if (byTags) searcher.and().addStatement("t.id IN (:tagIds)", Collections.singletonMap("tagIds", parameters.getTagIds()));

        if (!ListUtils.isEmpty(parameters.getTypeIds()))
            searcher.and().addStatement("mediaType.id IN (:typeIds)", Collections.singletonMap("typeIds", parameters.getTypeIds()));

        searcher.tryAddSearchStringCondition("name", parameters.getSearchString());

        if (parameters.getRandomOrder())
            searcher.orderRandom();

        return searcher.find(parameters.getPageIndex(), parameters.getPageSize());
    }

    private void collectCategoryAndSubcategoryIds(Category category, List<Long> categoryIds) {
        categoryIds.add(category.getId());

        List<Category> categories = categoryRepository.findByParentCategoryId(category.getId());
        for (Category subcategory : categories) {
            collectCategoryAndSubcategoryIds(subcategory, categoryIds);
        }
    }

    @Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }
}
