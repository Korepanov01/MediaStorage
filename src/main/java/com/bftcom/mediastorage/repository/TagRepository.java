package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

public interface TagRepository extends ParametersSearchRepository<Tag, SearchStringParameters>,
        NameSearchRepository<Tag> {
}
