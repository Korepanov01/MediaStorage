package com.bftcom.mediastorage.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaDto extends BaseDto{
    private final Long id;
    private final String name;
    private final String description;
    private final List<String> mainFilesUrls;

    private final List<TagDto> tags;
    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;
}
