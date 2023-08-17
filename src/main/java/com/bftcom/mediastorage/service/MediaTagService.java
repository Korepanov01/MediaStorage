package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.repository.MediaTagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaTagService {

    private final MediaTagRepository mediaTagRepository;

    @Transactional
    public void save(@NonNull Long mediaId, @NonNull Long tagId) throws EntityNotFoundException, TooManyTagsException, EntityAlreadyExistsException {
        if (mediaTagRepository.mediaTagsCount(mediaId) > 20)
            throw new TooManyTagsException("Слишком много тегов");

        if (mediaTagRepository.existsByMediaIdTagId(mediaId, tagId))
            throw new EntityAlreadyExistsException("Тег уже принадлежит медиа");

        mediaTagRepository.save(new MediaTag(mediaId, tagId));
    }

    @Transactional
    public void delete(@NonNull Long mediaId, @NonNull Long tagId) throws EntityNotFoundException {
        Optional<MediaTag> optionalEntity = mediaTagRepository.findByMediaIdTagId(mediaId, tagId);

        mediaTagRepository.delete(optionalEntity
                .orElseThrow(()
                        -> new EntityNotFoundException("Тег не принадлежит медиа")));
    }
}
