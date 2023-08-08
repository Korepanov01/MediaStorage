package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.api.request.PostPutEntityRequest;

public interface SaveDeleteController<
        Entity extends BaseEntity,
        PostRequest extends PostPutEntityRequest<Entity>>
        extends  SaveController<Entity, PostRequest>, DeleteController<Entity> {
}
