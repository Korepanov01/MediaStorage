package com.bftcom.mediastorage.model.dto;

import lombok.*;

import java.util.List;

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
}
