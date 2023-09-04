package com.bftcom.mediastorage.web.model.dto;

import com.bftcom.mediastorage.data.entity.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MediaTypeDto {
    private final Long id;
    private final String name;

    public MediaTypeDto(@NonNull MediaType mediaType) {
        this(mediaType.getId(), mediaType.getName());
    }
}
