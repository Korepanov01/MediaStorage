package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.api.request.PostMediaRequest;
import com.bftcom.mediastorage.model.api.request.PutMediaRequest;
import com.bftcom.mediastorage.model.dto.MediaDto;
import com.bftcom.mediastorage.model.dto.MediaListItemDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.service.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
@Transactional
public class MediaController implements FullController<
        MediaDto,
        MediaListItemDto,
        Media,
        PostMediaRequest,
        PutMediaRequest,
        MediaSearchParameters> {

    private final MediaService mediaService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final MediaTypeService mediaTypeService;

    @Override
    public MediaListItemDto convertToListItemDto(@NonNull Media media) {
        return new MediaListItemDto(media);
    }

    @Override
    public MediaDto convertToDto(@NonNull Media media) {
        return new MediaDto(media);
    }

    @Override
    public ParameterSearchService<Media, MediaSearchParameters> getMainService() {
        return mediaService;
    }

    @Override
    public Media fillEntity(@NonNull PostMediaRequest postMediaRequest) throws EntityNotFoundException {
        String description = StringUtils.hasText(postMediaRequest.getDescription()) ? postMediaRequest.getDescription() : null;

        Category category = categoryService.findById(postMediaRequest.getCategoryId());
        if (category == null) throw new EntityNotFoundException("Категория не найдена");

        MediaType mediaType = mediaTypeService.findById(postMediaRequest.getMediaTypeId());
        if (mediaType == null) throw new EntityNotFoundException("Тип медиа не найден");

        User user = userService.findById(postMediaRequest.getUserId());
        if (user == null) throw new EntityNotFoundException("Пользователь не найден");

        return new Media(
                postMediaRequest.getName(),
                description,
                category,
                mediaType,
                user
        );
    }

    @Override
    public void updateEntity(@NonNull Media media, @NonNull PutMediaRequest putMediaRequest) throws EntityNotFoundException {
        String description = StringUtils.hasText(putMediaRequest.getDescription()) ? putMediaRequest.getDescription() : null;

        Category category = categoryService.findById(putMediaRequest.getCategoryId());
        if (category == null) throw new EntityNotFoundException("Категория не найдена");

        MediaType mediaType = mediaTypeService.findById(putMediaRequest.getMediaTypeId());
        if (mediaType == null) throw new EntityNotFoundException("Тип медиа не найден");

        media.setName(putMediaRequest.getName());
        media.setDescription(description);
        media.setCategory(category);
        media.setMediaType(mediaType);
    }
}
