package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagDto extends BaseDto {
    private final Long id;
    private final String name;

    public TagDto(@NonNull Tag tag) {
        this(tag.getId(), tag.getName());
    }
}
