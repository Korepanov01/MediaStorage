package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

public interface MediaTypeRepository extends ParametersSearchRepository<MediaType, SearchStringParameters>,
        NameSearchRepository<MediaType> {
}
