package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.MediaTypeRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaTypeService extends ParameterSearchService<MediaType, SearchStringParameters> {

    private final MediaTypeRepository mediaTypeRepository;

    @Override
    protected ParametersSearchRepository<MediaType, SearchStringParameters> getMainRepository() {
        return mediaTypeRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull MediaType mediaType) {
        return mediaTypeRepository.findByName(mediaType.getName()).isPresent();
    }
}
