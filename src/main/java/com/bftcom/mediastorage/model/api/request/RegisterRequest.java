package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 200, message = "Имя должно быть не больше 200 символов")
    private String name;

    @NotBlank(message = "Почта не должна быть пустой")
    @Size(max = 500, message = "Почта должна быть не больше 500 символов")
    @Email(message = "Некорректный адрес электронной почты")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен иметь больше 8 символов")
    @Size(max = 100, message = "Пароль не должен быть больше 100 символов")
    private String password;

    public User covertToEntity(@NonNull PasswordEncoder passwordEncoder) {
        return new User(name, passwordEncoder.encode(password), email);
    }
}
