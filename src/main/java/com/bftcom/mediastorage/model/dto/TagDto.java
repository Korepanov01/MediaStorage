package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Tag;

public class TagDto extends BaseDto {

    private final Long id;

    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagDto(Tag tag) {
        this(tag.getId(), tag.getName());
    }

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
