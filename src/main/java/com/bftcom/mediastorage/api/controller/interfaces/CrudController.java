package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.entity.Identical;

public interface CrudController <
        Dto,
        Entity extends Identical,
        PostRequest,
        PutRequest
        >
        extends
        GetByIdController<Dto, Entity>,
        SaveController<Entity, PostRequest>,
        DeleteController<Entity>,
        PutController<Entity, PutRequest> {

}