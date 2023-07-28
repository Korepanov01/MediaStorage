package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.MediaTagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaTagService extends SaveDeleteService<MediaTag> {

    private final MediaTagRepository mediaTagRepository;

    @Override
    protected CrudRepository<MediaTag> getMainRepository() {
        return mediaTagRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaTag mediaTag) {
        return mediaTagRepository.isExists(mediaTag.getMediaId(), mediaTag.getTagId());
    }
}
