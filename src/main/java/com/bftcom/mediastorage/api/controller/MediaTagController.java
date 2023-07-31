package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.base.SaveController;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.model.request.mediatag.DeleteMediaTagRequest;
import com.bftcom.mediastorage.model.request.mediatag.PostMediaTag;
import com.bftcom.mediastorage.service.MediaTagService;
import com.bftcom.mediastorage.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/media_tag")
@RequiredArgsConstructor
public class MediaTagController extends SaveController<MediaTag, PostMediaTag> {

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
    protected CrudService<MediaTag> getMainService() {
        return mediaTagService;
    }
}
