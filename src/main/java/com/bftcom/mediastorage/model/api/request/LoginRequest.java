package com.bftcom.mediastorage.model.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Почта не должна быть пустой")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
