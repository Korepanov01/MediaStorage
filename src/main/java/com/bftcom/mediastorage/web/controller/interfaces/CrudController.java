package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.data.entity.BaseEntity;

public interface CrudController <
        Dto,
        Entity extends BaseEntity,
        PostRequest,
        PutRequest
        >
        extends
        GetByIdController<Dto, Entity>,
        SaveController<Entity, PostRequest>,
        DeleteController<Entity>,
        PutController<Entity, PutRequest> {

}