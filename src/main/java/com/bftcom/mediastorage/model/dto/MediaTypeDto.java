package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaTypeDto extends BaseDto {
    private final Long id;
    private final String name;

    public MediaTypeDto(@NonNull MediaType mediaType) {
        this(mediaType.getId(), mediaType.getName());
    }
}
