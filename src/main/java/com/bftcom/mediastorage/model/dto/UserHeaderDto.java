package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.User;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class UserHeaderDto {
    private final Long id;
    private final String name;

    public UserHeaderDto(@NonNull User user) {
        id = user.getId();
        name = user.getName();
    }
}
