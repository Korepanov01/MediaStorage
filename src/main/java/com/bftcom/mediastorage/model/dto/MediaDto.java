package com.bftcom.mediastorage.model.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class MediaDto extends MediaListItemDto {

    private final String description;

    private final LocalDateTime createdAt;

    private final LocalDateTime editedAt;

    public MediaDto(MediaListItemDto mediaListItemDto, String description, LocalDateTime createdAt, LocalDateTime editedAt) {
        super(mediaListItemDto.getId(), mediaListItemDto.getName(), mediaListItemDto.getUser(), mediaListItemDto.getCategory(), mediaListItemDto.getMediaType());
        this.description = description;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
    }
}
