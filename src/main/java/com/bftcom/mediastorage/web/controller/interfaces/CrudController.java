package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.data.entity.Identical;

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