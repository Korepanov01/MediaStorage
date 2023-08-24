package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.GetAllController;
import com.bftcom.mediastorage.api.controller.interfaces.GetByIdController;
import com.bftcom.mediastorage.model.dto.MediaTypeDto;
import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.MediaTypeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media_type")
@RequiredArgsConstructor
public class MediaTypeController implements
        GetAllController<MediaTypeDto, MediaType>,
        GetByIdController<MediaTypeDto, MediaType> {

    private final MediaTypeService mediaTypeService;

    @Override
    public MediaTypeDto convertToListItemDto(@NonNull MediaType mediaType) {
        return new MediaTypeDto(mediaType);
    }

    @Override
    public MediaTypeDto convertToDto(@NonNull MediaType mediaType) {
        return new MediaTypeDto(mediaType);
    }

    @Override
    public CrudService<MediaType> getMainService() {
        return mediaTypeService;
    }
}
