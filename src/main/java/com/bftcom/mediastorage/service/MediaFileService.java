package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.searchparameters.MediaFilesSearchParameters;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileService extends ParameterSearchService<MediaFile, MediaFilesSearchParameters> {

    private final MediaFileRepository repository;

    @Override
    protected ParametersSearchRepository<MediaFile, MediaFilesSearchParameters> getMainRepository() {
        return repository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaFile entity) {
        return false;
    }
}
