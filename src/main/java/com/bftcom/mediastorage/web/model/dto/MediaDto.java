package com.bftcom.mediastorage.web.model.dto;

import com.bftcom.mediastorage.data.entity.Media;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class MediaDto {
    private final Long id;
    private final String name;
    private final String description;
    private final List<FileInfoDto> files;

    private final List<TagDto> tags;
    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;

    public MediaDto(Media media) {
        this(
                media.getId(),
                media.getName(),
                media.getDescription(),
                media.getFiles().stream().map(FileInfoDto::new).collect(Collectors.toList()),
                media.getTags().stream().map(TagDto::new).collect(Collectors.toList()),
                new UserHeaderDto(media.getUser()),
                new CategoryDto(media.getCategory()),
                new MediaTypeDto(media.getMediaType())
        );
    }
}
