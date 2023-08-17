package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.*;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserHeaderDto extends BaseDto {
    private final Long id;
    private final String name;

    public UserHeaderDto(@NonNull User user) {
        id = user.getId();
        name = user.getName();
    }
}
