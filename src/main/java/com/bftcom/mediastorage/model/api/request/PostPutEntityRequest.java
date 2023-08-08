package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.BaseEntity;

public abstract class PostPutEntityRequest<Entity extends BaseEntity>
        extends Request
        implements ToEntityConvertable<Entity> {

    @Override
    public abstract Entity covertToEntity();
}
