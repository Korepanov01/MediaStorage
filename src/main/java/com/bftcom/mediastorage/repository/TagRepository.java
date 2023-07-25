package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag> {

    List<Tag> findByParameters(SearchStringParameters parameters);

    Optional<Tag> findByName(String name);
}
