package com.bftcom.mediastorage.web.model.response;

import com.bftcom.mediastorage.data.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostEntityResponse {

    private final Long id;

    public PostEntityResponse(BaseEntity entity) {
        id = entity.getId();
    }
}
