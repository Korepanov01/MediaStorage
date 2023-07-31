package com.bftcom.mediastorage.model.request.delete;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteUserRoleRequest extends DeleteEntityRequest {

    @NotNull(message = "Должен быть указан id роли (roleId)")
    private Long roleId;

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;
}
