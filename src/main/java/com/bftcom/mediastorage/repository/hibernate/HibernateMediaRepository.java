package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.MediaRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.util.Collections;
import java.util.List;

@Repository
public class HibernateMediaRepository extends HibernateCrudRepository<Media> implements MediaRepository {

    @Override
    @Transactional(readOnly = true)
    public List<Media> findByParameters(@NonNull MediaSearchParameters parameters) {
        boolean byCategory = parameters.getCategoryId() != null;
        boolean byTags = parameters.getTagIds() != null && !parameters.getTagIds().isEmpty();

        ParametersSearcher searcher = this.new ParametersSearcher();

        searcher.selectFrom();

        if (byTags) {
            searcher.addStatement("JOIN m.tags t");
        }

        searcher.where();

        if (byTags) searcher.and().addStatement("t.id IN (:tagIds)", Collections.singletonMap("tagIds", parameters.getTagIds()));

        if (!ListUtils.isEmpty(parameters.getTypeIds()))
            searcher.and().addStatement("mediaType.id IN (:typeIds)", Collections.singletonMap("typeIds", parameters.getTypeIds()));

        return searcher
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .find(parameters.getPageIndex(), parameters.getPageSize());
    }

    @Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }
}
