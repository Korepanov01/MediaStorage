package com.bftcom.mediastorage.model.request.role;

import com.bftcom.mediastorage.model.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostRoleRequest {
    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name length must be no more than 100")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public Role convertToTag(PostRoleRequest request) {
        return new Role(request.getName());
    }
}
