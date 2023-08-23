package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.model.api.response.PostEntityResponse;
import com.bftcom.mediastorage.model.entity.Identical;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

public interface SaveController<
        Entity extends Identical,
        PostRequest> {

    CrudService<Entity> getMainService();

    @PostMapping
    default ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostRequest request) {
        Entity entity = fillEntity(request);

        try {
            getMainService().save(entity);
        } catch (EntityExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(entity));
    }

    Entity fillEntity(PostRequest postRequest);
}
