package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.data.entity.Identical;

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