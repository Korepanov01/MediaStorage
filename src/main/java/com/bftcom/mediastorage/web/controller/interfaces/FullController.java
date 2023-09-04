package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.data.entity.BaseEntity;

public interface FullController <
        Dto,
        ListItemDto,
        Entity extends BaseEntity,
        PostRequest,
        PutRequest,
        SearchParameters>
        extends
        ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        CrudController<Dto, Entity, PostRequest, PutRequest> {
}