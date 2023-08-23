package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.Identical;

public interface CrudController <
        Dto extends BaseDto,
        Entity extends Identical,
        PostRequest extends ToEntityConvertable<Entity>
        >
        extends
        GetByIdController<Dto, Entity>,
        SaveController<Entity, PostRequest>,
        DeleteController<Entity> {

}