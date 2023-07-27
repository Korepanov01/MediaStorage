package com.bftcom.mediastorage.model.request.role;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostRoleRequest extends PostEntityRequest<Role> {
    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name length must be no more than 100")
    private String name;

    @Override
    public Role covertToEntity() {
        return new Role(name);
    }
}
