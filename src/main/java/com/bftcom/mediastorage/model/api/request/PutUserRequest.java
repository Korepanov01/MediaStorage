package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PutUserRequest implements ToEntityConvertable<User> {

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 200, message = "Имя должно быть не больше 200 символов")
    private String name;

    @NotBlank(message = "Почта не должна быть пустой")
    @Size(max = 500, message = "Почта должна быть не больше 500 символов")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен иметь больше 8 символов")
    @Size(max = 100, message = "Пароль не должен быть больше 100 символов")
    private String password;

    @Override
    public User covertToEntity() {
        return new User(
                name,
                String.valueOf(password.hashCode()),
                email);
    }
}
