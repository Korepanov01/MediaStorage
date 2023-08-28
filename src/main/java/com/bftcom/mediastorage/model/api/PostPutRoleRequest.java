package com.bftcom.mediastorage.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PostPutRoleRequest {

    @NotBlank(message = "Имя роли не может быть пустым")
    @Size(max = 100, message = "Имя роли не может быть длиннее 100 символов")
    private String name;
}
