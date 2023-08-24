package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
@Transactional
public class MediaTagController {

    private final MediaService mediaService;

    @PostMapping("/{id}/add_tag")
    public ResponseEntity<?> addTag(
            @PathVariable
            Long id,
            @RequestParam
            Long tagId) {
        try {
            mediaService.addTag(id, tagId);
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        } catch (TooManyTagsException e) {
            return Response.getBadRequest(e.getMessage());
        }

        return Response.getOk();
    }

    @DeleteMapping("/{id}/remove_tag")
    public ResponseEntity<?> removeTag(
            @PathVariable
            Long id,
            @RequestParam
            Long tagId) {
        try {
            mediaService.removeTag(id, tagId);
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        }

        return Response.getOk();
    }
}
