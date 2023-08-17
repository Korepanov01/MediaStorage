package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UserHeaderDto {
    private final String email;
    private final List<String> roles;

    public UserDto(@NonNull Long id, @NonNull String name, @NonNull String email, @NonNull List<String> roles) {
        super(id, name);
        this.email = email;
        this.roles = roles;
    }

    public UserDto(@NonNull User user, List<String> roles) {
        super(user);
        this.email = user.getEmail();
        this.roles = roles;
    }
}
