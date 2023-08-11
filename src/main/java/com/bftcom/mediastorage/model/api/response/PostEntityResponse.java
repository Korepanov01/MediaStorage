package com.bftcom.mediastorage.model.api.response;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostEntityResponse {

    private final Long id;

    public static PostEntityResponse convertFromEntity(BaseEntity entity) {
        return new PostEntityResponse(entity.getId());
    }
}
