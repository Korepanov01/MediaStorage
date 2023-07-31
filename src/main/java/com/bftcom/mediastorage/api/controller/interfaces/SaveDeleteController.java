package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.request.PostEntityRequest;

public interface SaveDeleteController<
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>>
        extends  SaveController<Entity, PostRequest>, DeleteController<Entity> {
}
