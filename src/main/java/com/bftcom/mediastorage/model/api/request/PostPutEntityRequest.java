package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.api.request.Request;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;

public abstract class PostPutEntityRequest<Entity extends BaseEntity>
        extends Request
        implements ToEntityConvertable<Entity> {

    @Override
    public abstract Entity covertToEntity();
}
