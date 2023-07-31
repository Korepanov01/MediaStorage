package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.MediaListItemDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.model.request.post.PostMediaRequest;
import com.bftcom.mediastorage.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor

public class MediaController implements FullController<MediaListItemDto, Media, PostMediaRequest, MediaSearchParameters> {

    private final MediaService mediaService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final MediaTypeService mediaTypeService;

    @Override
    public MediaListItemDto convertToDto(Media media) {
        User user = userService.findById(media.getUserId()).orElseThrow();

        Category category = categoryService.findById(media.getCategoryId()).orElseThrow();

        MediaType mediaType = mediaTypeService.findById(media.getMediaTypeId()).orElseThrow();

        return new MediaListItemDto(media, user, category, mediaType, null);
    }

    @Override
    public ParameterSearchService<Media, MediaSearchParameters> getMainService() {
        return mediaService;
    }
}
