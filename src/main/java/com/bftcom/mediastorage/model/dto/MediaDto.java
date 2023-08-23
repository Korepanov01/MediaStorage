package com.bftcom.mediastorage.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MediaDto {
    private final Long id;
    private final String name;
    private final String description;
    private final List<FileInfoDto> files;

    private final List<TagDto> tags;
    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;
}
