package com.bftcom.mediastorage.web.model.dto;

import com.bftcom.mediastorage.data.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TagDto {
    private final Long id;
    private final String name;

    public TagDto(@NonNull Tag tag) {
        this(tag.getId(), tag.getName());
    }
}
