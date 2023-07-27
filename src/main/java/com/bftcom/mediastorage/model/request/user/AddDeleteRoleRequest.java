package com.bftcom.mediastorage.model.request.user;

import com.bftcom.mediastorage.model.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddDeleteRoleRequest extends Request {

    @NotNull(message = "Id не может быть пустым")
    private Long roleId;
}
