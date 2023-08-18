package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;

public interface FullController <
        Dto extends BaseDto,
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
        PostRequest extends ToEntityConvertable<Entity>,
        PutRequest extends ToEntityConvertable<Entity>,
        SearchParameters>
        extends
        ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        PutController<Entity, PutRequest>,
        SaveDeleteController<Entity, PostRequest>,
        GetByIdController<Dto, Entity> {
}