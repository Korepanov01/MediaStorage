package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.MediaTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaTypeService extends CrudService<MediaType> {

    private final MediaTypeRepository mediaTypeRepository;

    @Override
    protected CustomJpaRepository<MediaType> getMainRepository() {
        return mediaTypeRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull MediaType mediaType) {
        return mediaTypeRepository.existsByName(mediaType.getName());
    }
}
