package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.Identical;

public interface FullController <
        Dto extends BaseDto,
        ListItemDto extends BaseDto,
        Entity extends Identical,
        PostRequest extends ToEntityConvertable<Entity>,
        SearchParameters>
        extends
        ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        CrudController<Dto, Entity, PostRequest> {
}