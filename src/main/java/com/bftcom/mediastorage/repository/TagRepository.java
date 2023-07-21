package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findByParameters(SearchStringParameters parameters);
}
