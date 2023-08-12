package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.searchparameters.PagingParameters;

public interface FullController <
        Dto extends BaseDto,
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
        PostRequest extends ToEntityConvertable<Entity>,
        PutRequest extends ToEntityConvertable<Entity>,
        SearchParameters extends PagingParameters>
        extends
        ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        PutController<Entity, PutRequest>,
        SaveDeleteController<Entity, PostRequest>,
        GetByIdController<Dto, Entity> {
}