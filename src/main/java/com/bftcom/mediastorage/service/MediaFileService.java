package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileService extends CrudService<MediaFile> {

    private final MediaFileRepository repository;

    @Override
    protected CrudRepository<MediaFile> getMainRepository() {
        return repository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaFile entity) {
        return false;
    }
}
