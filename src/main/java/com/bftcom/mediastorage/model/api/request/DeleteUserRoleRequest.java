package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class DeleteUserRoleRequest {

    @NotNull(message = "Должен быть указан id роли (roleId)")
    private Long roleId;

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;
}
