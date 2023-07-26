package com.bftcom.mediastorage.model.request;

import com.bftcom.mediastorage.model.entity.BaseEntity;

public abstract class PostEntityRequest<Entity extends BaseEntity>
        extends Request
        implements ToEntityConvertable<Entity>{

    @Override
    public abstract Entity covertToEntity();
}
