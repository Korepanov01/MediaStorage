package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class PostUserRoleRequest implements ToEntityConvertable<UserRole> {

    @NotNull(message = "Должен быть указан id роли (roleId)")
    private Long roleId;

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;

    @Override
    public UserRole covertToEntity() {
        return new UserRole(roleId, userId);
    }
}
