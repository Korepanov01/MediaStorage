package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.searchparameters.PagingParameters;
import com.bftcom.mediastorage.model.api.request.post.PostEntityRequest;

public interface FullController <
        Dto extends BaseDto,
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>,
        SearchParameters extends PagingParameters>
        extends ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        SaveDeleteController<Entity, PostRequest>,
        GetByIdController<Dto, Entity> {
}
