package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto {
    private final Long id;
    private final String name;

    public RoleDto(@NonNull Role role) {
        this(role.getId(), role.getName());
    }
}
