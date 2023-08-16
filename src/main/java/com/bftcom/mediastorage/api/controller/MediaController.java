package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.api.request.PostMediaRequest;
import com.bftcom.mediastorage.model.api.request.PutMediaRequest;
import com.bftcom.mediastorage.model.dto.*;
import com.bftcom.mediastorage.model.entity.*;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    private final MediaFileService mediaFileService;
    private final TagService tagService;
    private final FileTypeService fileTypeService;

    @Override
    public MediaListItemDto convertToListItemDto(Media media) {
        User user = userService.findById(media.getUserId()).orElseThrow();

        Category category = categoryService.findById(media.getCategoryId()).orElseThrow();

        MediaType mediaType = mediaTypeService.findById(media.getMediaTypeId()).orElseThrow();

        List<Tag> tags = tagService.getByMediaId(media.getId());

        return new MediaListItemDto(
                media.getId(),
                media.getName(),
                mediaFileService.getThumbnailUrl(media.getId()),
                tags.stream().map(TagDto::new).collect(Collectors.toList()),
                new UserHeaderDto(user),
                new CategoryDto(category),
                new MediaTypeDto(mediaType)
        );
    }

    @Override
    public MediaDto convertToDto(Media media) {
        User user = userService.findById(media.getUserId()).orElseThrow();

        Category category = categoryService.findById(media.getCategoryId()).orElseThrow();

        MediaType mediaType = mediaTypeService.findById(media.getMediaTypeId()).orElseThrow();

        List<Tag> tags = tagService.getByMediaId(media.getId());

        List<MediaFile> mediaFiles = mediaFileService.getByMediaId(media.getId());

        List<FileInfoDto> fileInfoDtoList = mediaFiles.stream().map(mediaFile -> {
            FileType fileType = fileTypeService.findById(mediaFile.getFileTypeId()).orElseThrow();
            String url = FileService.getFileUrl(mediaFile.getFileId());
            return new FileInfoDto(mediaFile.getFileId(), url, fileType.getName());
        }).collect(Collectors.toList());

        return new MediaDto(
                media.getId(),
                media.getName(),
                media.getDescription(),
                fileInfoDtoList,
                tags.stream().map(TagDto::new).collect(Collectors.toList()),
                new UserHeaderDto(user),
                new CategoryDto(category),
                new MediaTypeDto(mediaType)
        );
    }

    @Override
    public ParameterSearchService<Media, MediaSearchParameters> getMainService() {
        return mediaService;
    }
}
