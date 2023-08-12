package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.entity.BaseEntity;

public interface SaveDeleteController<
        Entity extends BaseEntity,
        PostRequest extends ToEntityConvertable<Entity>>
        extends
        SaveController<Entity, PostRequest>,
        DeleteController<Entity> {
}
