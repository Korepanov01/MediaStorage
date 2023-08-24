package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateTagRepository extends HibernateCrudRepository<Tag> implements TagRepository {

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findByParameters(@NonNull SearchStringParameters parameters) {
        return this.new ParametersSearcher()
                .selectFrom()
                .where()
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .find(parameters.getPageIndex(), parameters.getPageSize());
    }

    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }
}
