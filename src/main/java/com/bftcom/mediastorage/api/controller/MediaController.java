package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.model.api.request.PostMediaRequest;
import com.bftcom.mediastorage.model.api.request.PutMediaRequest;
import com.bftcom.mediastorage.model.dto.MediaDto;
import com.bftcom.mediastorage.model.dto.MediaListItemDto;
import com.bftcom.mediastorage.model.entity.*;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.service.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
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

        Category category = categoryService.findById(postMediaRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        MediaType mediaType = mediaTypeService.findById(postMediaRequest.getMediaTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Тип медиа не найден"));

        User user = userService.findById(postMediaRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return new Media(
                postMediaRequest.getName(),
                description,
                category,
                mediaType,
                user
        );
    }

    @Override
    public void updateEntity(@NonNull Media media, @NonNull PutMediaRequest request) throws EntityNotFoundException, IllegalOperationException {
        String description = StringUtils.hasText(request.getDescription()) ? request.getDescription() : null;

        Category category = categoryService.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        MediaType mediaType = mediaTypeService.findById(request.getMediaTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Тип медиа не найден"));
        if (!media.getMediaType().getId().equals(mediaType.getId()) && media.getFiles().stream().anyMatch(mediaFile -> mediaFile.getFileType().getName().equals(FileType.MAIN)))
            throw new IllegalOperationException("Нельзя менять тип, если есть файлы");

        media.setName(request.getName());
        media.setDescription(description);
        media.setCategory(category);
        media.setMediaType(mediaType);
    }
}
