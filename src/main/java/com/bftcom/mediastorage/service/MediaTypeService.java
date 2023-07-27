package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.MediaTypeRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaTypeService extends BaseService<MediaType, SearchStringParameters> {

    private final MediaTypeRepository mediaTypeRepository;

    @Override
    protected ParametersSearchRepository<MediaType, SearchStringParameters> getMainRepository() {
        return mediaTypeRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaType entity) {
        return false;
    }
}
