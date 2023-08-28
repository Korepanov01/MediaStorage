package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Responses;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
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
            return Responses.notFound(e.getMessage());
        } catch (TooManyTagsException e) {
            return Responses.badRequest(e.getMessage());
        }

        return Responses.OK;
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
            return Responses.notFound(e.getMessage());
        }

        return Responses.OK;
    }
}
