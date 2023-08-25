package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.MediaRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MediaService extends CrudService<Media> {

    private final MediaRepository mediaRepository;
    private final TagRepository tagRepository;

    public void addTag(@NonNull Long mediaId, @NonNull Long tagId)
            throws EntityNotFoundException, TooManyTagsException {
        addOrRemoveTag(mediaId, tagId, false);
    }

    public void removeTag(@NonNull Long mediaId, @NonNull Long tagId)
            throws EntityNotFoundException {
        try {
            addOrRemoveTag(mediaId, tagId, true);
        } catch (TooManyTagsException ignored) { }
    }

    private void addOrRemoveTag(@NonNull Long mediaId, @NonNull Long tagId, boolean isRemove)
            throws EntityNotFoundException, TooManyTagsException {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new EntityNotFoundException("Медиа не найдена"));

        if (!isRemove && media.getTags().size() > 20)
            throw new TooManyTagsException("Тегов не может быть больше 20-и");

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("Тег не найден"));

        if (isRemove)
            media.removeTag(tag);
        else
            media.addTag(tag);
    }

    @Override
    protected CustomJpaRepository<Media> getMainRepository() {
        return mediaRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Media entity) {
        return false;
    }
}
