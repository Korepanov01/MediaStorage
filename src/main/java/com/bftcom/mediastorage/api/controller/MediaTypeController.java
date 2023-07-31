package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.MediaTypeDto;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.model.api.request.post.PostMediaTypeRequest;
import com.bftcom.mediastorage.service.MediaTypeService;
import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/media_type")
@RequiredArgsConstructor
public class MediaTypeController implements FullController<
        MediaTypeDto,
        MediaTypeDto,
        MediaType,
        PostMediaTypeRequest,
        SearchStringParameters> {

    private final MediaTypeService mediaTypeService;

    @Override
    public MediaTypeDto convertToListItemDto(MediaType mediaType) {
        return new MediaTypeDto(mediaType);
    }

    @Override
    public MediaTypeDto convertToDto(MediaType mediaType) {
        return new MediaTypeDto(mediaType);
    }

    @Override
    public ParameterSearchService<MediaType, SearchStringParameters> getMainService() {
        return mediaTypeService;
    }
}
