package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    private Long id;
    private String name;

    public UserDto(@NonNull User user) {
        this(user.getId(), user.getName());
    }
}
