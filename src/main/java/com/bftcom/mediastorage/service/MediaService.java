package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.MediaRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService extends ParameterSearchService<Media, MediaSearchParameters> {

    private final MediaRepository mediaRepository;
    private final CategoryService categoryService;
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
    public List<Media> findByParameters(MediaSearchParameters parameters) throws EntityNotFoundException {
        return mediaRepository.findByParameters(
                parameters.getCategoryId() != null ? categoryService.findAllSubcategoryIds(parameters.getCategoryId()) : null,
                parameters.getTagIds(),
                parameters.getTypeIds(),
                parameters.getSearchString(),
                parameters.getUserId(),
                //parameters.getRandomOrder() ? new Random().nextInt() : null,
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
