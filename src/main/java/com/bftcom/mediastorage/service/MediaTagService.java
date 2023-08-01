package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.MediaTagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaTagService extends CrudService<MediaTag> {

    private final MediaTagRepository mediaTagRepository;

    @Override
    protected CrudRepository<MediaTag> getMainRepository() {
        return mediaTagRepository;
    }

    @Transactional
    public void delete(@NonNull Long mediaId, @NonNull Long tagId) throws EntityNotFoundException {
        Optional<MediaTag> optionalEntity = mediaTagRepository.findByMediaIdTagId(mediaId, tagId);

        mediaTagRepository.delete(optionalEntity
                .orElseThrow(()
                        -> new EntityNotFoundException(String.format("Тег id(%d) не принадлежит медиа id(%d)", tagId, mediaId))));
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaTag mediaTag) {
        return mediaTagRepository.isExists(mediaTag.getMediaId(), mediaTag.getTagId());
    }
}
