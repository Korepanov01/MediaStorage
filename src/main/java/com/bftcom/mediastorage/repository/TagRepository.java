package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.TagSearchParameters;

public interface TagRepository extends ParametersSearchRepository<Tag, TagSearchParameters>,
        NameSearchRepository<Tag> {
}
