package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.MediaRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService extends ParameterSearchService<Media, MediaSearchParameters> {

    private final MediaRepository mediaRepository;

    @Override
    protected ParametersSearchRepository<Media, MediaSearchParameters> getMainRepository() {
        return mediaRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Media entity) {
        return false;
    }
}
