package com.bftcom.mediastorage.model.response;

import com.bftcom.mediastorage.model.entity.BaseEntity;

public class PostEntityResponse {

    private final Long id;

    public PostEntityResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static PostEntityResponse convertFromEntity(BaseEntity entity) {
        return new PostEntityResponse(entity.getId());
    }
}
