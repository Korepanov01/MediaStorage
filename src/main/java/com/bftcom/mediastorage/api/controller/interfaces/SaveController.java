package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Responses;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.model.api.PostEntityResponse;
import com.bftcom.mediastorage.model.entity.Identical;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface SaveController<
        Entity extends Identical,
        PostRequest> {

    CrudService<Entity> getMainService();

    @Transactional
    @PostMapping
    default ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostRequest request) {
        Entity entity;

        try {
            entity = fillEntity(request);
        } catch (Exception e) {
            return Responses.badRequest(e.getMessage());
        }

        try {
            getMainService().save(entity);
        } catch (EntityExistsException exception) {
            return Responses.badRequest(exception.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(entity));
    }

    Entity fillEntity(@NonNull PostRequest postRequest) throws Exception;
}
