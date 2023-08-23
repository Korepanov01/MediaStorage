package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.service.FileService;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class MediaListItemDto {
    private final Long id;
    private final String name;
    private final String thumbnailUrl;

    private final List<TagDto> tags;
    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;

    public MediaListItemDto(Media media) {
        this(
                media.getId(),
                media.getName(),
                media.getFiles().stream().filter(file -> file.getFileType().getName().equals(FileType.THUMBNAIL)).findFirst().map(file ->  FileService.getFileUrl(file.getId())).orElse(null),
                media.getTags().stream().map(TagDto::new).collect(Collectors.toList()),
                new UserHeaderDto(media.getUser()),
                new CategoryDto(media.getCategory()),
                new MediaTypeDto(media.getMediaType())
        );
    }
}
