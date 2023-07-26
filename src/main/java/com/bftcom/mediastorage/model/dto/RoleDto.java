package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Role;

public class RoleDto extends BaseDto {

    private final Long id;

    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoleDto(Role role) {
        this(role.getId(), role.getName());
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
