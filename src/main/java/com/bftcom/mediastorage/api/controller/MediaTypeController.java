package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.MediaTypeDto;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.mediatype.PostMediaTypeRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.MediaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/media_type")
@RequiredArgsConstructor
public class MediaTypeController extends CrudController<
        MediaTypeDto,
        MediaType,
        PostMediaTypeRequest,
        SearchStringParameters> {

    private final MediaTypeService mediaTypeService;

    @Override
    protected MediaTypeDto convertToDto(MediaType mediaType) {
        return new MediaTypeDto(mediaType);
    }

    @Override
    protected ParameterSearchService<MediaType, SearchStringParameters> getMainService() {
        return mediaTypeService;
    }
}
