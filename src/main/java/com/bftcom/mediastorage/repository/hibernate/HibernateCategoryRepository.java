package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.repository.CategoryRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateCategoryRepository extends HibernateCrudRepository<Category> implements CategoryRepository {

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findByParentCategoryId(@NonNull Long parentCategoryId) {
        ParametersSearcher searcher = this.new ParametersSearcher()
                .selectFrom()
                .where();

        if (parentCategoryId != 0)
            searcher.addEqualsCondition("parentCategory.id", parentCategoryId);
        else
            searcher.addStatement("AND parentCategory.id IS NULL");

        return searcher.find();
    }
}
