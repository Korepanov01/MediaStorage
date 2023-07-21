package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Tag;

public class TagDto {

    private final Long id;

    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static public TagDto ConvertToDto(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }
}
