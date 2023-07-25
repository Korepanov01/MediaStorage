package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.Optional;

public interface TagRepository extends ParametersSearchRepository<Tag, SearchStringParameters> {

    Optional<Tag> findByName(String name);
}
