package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.data.entity.Media;
import com.bftcom.mediastorage.data.entity.Tag;
import com.bftcom.mediastorage.web.model.parameter.MediaSearchParameters;
import com.bftcom.mediastorage.data.repository.CategoryRepository;
import com.bftcom.mediastorage.data.repository.CustomJpaRepository;
import com.bftcom.mediastorage.data.repository.MediaRepository;
import com.bftcom.mediastorage.data.repository.TagRepository;
import com.bftcom.mediastorage.data.repository.spicification.MediaSpecifications;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService extends ParameterSearchService<Media, MediaSearchParameters> {

    private final MediaRepository mediaRepository;
    private final CategoryRepository categoryRepository;
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

        mediaRepository.save(media);
    }

    @Override
    public List<Media> findByParameters(MediaSearchParameters parameters) {
        return mediaRepository.findAll(
                MediaSpecifications.findByParameters(
                        parameters.getCategoryId() != null ? categoryRepository.findAllSubcategoryIds(parameters.getCategoryId()) : null,
                        parameters.getTagIds(),
                        parameters.getTypeIds(),
                        parameters.getSearchString(),
                        parameters.getUserId(),
                        parameters.getRandomOrder()),
                PageRequest.of(parameters.getPageIndex(), parameters.getPageSize()));
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
