package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class MediaDto extends BaseDto{
    private final Long id;
    private final String name;

    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;

    private final String description;

    private final LocalDateTime createdAt;

    private final LocalDateTime editedAt;

    public MediaDto(Media media, User user, Category category, MediaType mediaType) {
        this.id = media.getId();
        this.name = media.getName();
        this.user = new UserHeaderDto(user);
        this.category = new CategoryDto(category);
        this.mediaType = new MediaTypeDto(mediaType);
        this.description = media.getDescription();
        this.createdAt = media.getCreatedAt();
        this.editedAt = media.getEditedAt();
    }
}
