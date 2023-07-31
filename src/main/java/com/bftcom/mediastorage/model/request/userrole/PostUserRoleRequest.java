package com.bftcom.mediastorage.model.request.userrole;

import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostUserRoleRequest extends PostEntityRequest<UserRole> {

    @NotNull(message = "Должен быть указан id роли (roleId)")
    private Long roleId;

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;

    @Override
    public UserRole covertToEntity() {
        return new UserRole(roleId, userId);
    }
}
