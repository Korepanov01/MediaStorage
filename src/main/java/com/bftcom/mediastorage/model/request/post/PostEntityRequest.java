package com.bftcom.mediastorage.model.request.post;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.request.Request;
import com.bftcom.mediastorage.model.request.ToEntityConvertable;

public abstract class PostEntityRequest<Entity extends BaseEntity>
        extends Request
        implements ToEntityConvertable<Entity> {

    @Override
    public abstract Entity covertToEntity();
}
