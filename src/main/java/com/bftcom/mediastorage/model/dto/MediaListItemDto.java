package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.entity.User;
import lombok.*;

@Data
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaListItemDto extends BaseDto {
    private final Long id;
    private final String name;

    private final UserHeaderDto user;
    private final CategoryDto category;
    private final MediaTypeDto mediaType;

    public MediaListItemDto(Media media, User user, Category category, MediaType mediaType) {
        this.id = media.getId();
        this.name = media.getName();
        this.user = new UserHeaderDto(user);
        this.category = new CategoryDto(category);
        this.mediaType = new MediaTypeDto(mediaType);
    }
}
