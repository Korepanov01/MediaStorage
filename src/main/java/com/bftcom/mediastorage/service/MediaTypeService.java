package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.data.entity.MediaType;
import com.bftcom.mediastorage.data.repository.CustomJpaRepository;
import com.bftcom.mediastorage.data.repository.MediaTypeRepository;
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
