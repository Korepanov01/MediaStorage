package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Role;

public class RoleDto {

    private final Long id;

    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static public RoleDto ConvertToDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }
}
