package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.MediaTypeDto;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.mediatype.PostMediaTypeRequest;
import com.bftcom.mediastorage.service.BaseService;

public class MediaTypeController extends BaseController<
        MediaTypeDto,
        MediaType,
        PostMediaTypeRequest,
        SearchStringParameters> {

    @Override
    protected MediaTypeDto convertToDto(MediaType entity) {
        return null;
    }

    @Override
    protected BaseService<MediaType, SearchStringParameters> getMainService() {
        return null;
    }
}
