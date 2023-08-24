package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.entity.Identical;

public interface FullController <
        Dto,
        ListItemDto,
        Entity extends Identical,
        PostRequest,
        PutRequest,
        SearchParameters>
        extends
        ParametersSearchController<ListItemDto, Entity, SearchParameters>,
        CrudController<Dto, Entity, PostRequest, PutRequest> {
}