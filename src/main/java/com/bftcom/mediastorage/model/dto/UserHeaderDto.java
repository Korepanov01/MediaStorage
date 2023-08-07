package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserHeaderDto extends BaseDto {
    private Long id;
    private String name;

    public UserHeaderDto(@NonNull User user) {
        this(user.getId(), user.getName());
    }
}
