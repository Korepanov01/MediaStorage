package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.SaveController;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.model.api.request.DeleteMediaTagRequest;
import com.bftcom.mediastorage.model.api.request.PostMediaTag;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.MediaTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/media_tag")
@RequiredArgsConstructor
public class MediaTagController implements SaveController<MediaTag, PostMediaTag> {

    private final MediaTagService mediaTagService;

    @DeleteMapping()
    public ResponseEntity<?> delete(
            @RequestBody
            @Valid
            DeleteMediaTagRequest request) {
        try {
            mediaTagService.delete(request.getMediaId(), request.getMediaId());
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        }
        return  Response.getOk();
    }

    @Override
    public CrudService<MediaTag> getMainService() {
        return mediaTagService;
    }
}
