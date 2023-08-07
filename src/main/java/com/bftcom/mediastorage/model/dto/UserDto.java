package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UserHeaderDto {
    private String email;

    public UserDto(@NonNull User user) {
        super(user);
        this.email = user.getEmail();
    }
}
