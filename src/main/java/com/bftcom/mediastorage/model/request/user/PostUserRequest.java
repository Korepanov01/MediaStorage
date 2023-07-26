package com.bftcom.mediastorage.model.request.user;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.request.PostEntityRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostUserRequest extends PostEntityRequest<User> {

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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    @Override
    public User covertToEntity() {
        return new User(
                name,
                String.valueOf(password.hashCode()),
                email);
    }
}
