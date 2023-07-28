package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {

    @NotNull
    private Long roleId;

    @NotNull
    private Long userId;

    public UserRole(Long id, Long roleId, Long userId) {
        super(id);
        this.roleId = roleId;
        this.userId = userId;
    }
}
