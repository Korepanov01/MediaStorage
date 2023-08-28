package com.bftcom.mediastorage.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class UpdateUserNameRequest {

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 200, message = "Имя должно быть не больше 200 символов")
    private String name;
}
