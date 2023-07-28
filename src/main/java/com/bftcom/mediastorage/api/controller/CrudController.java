package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class CrudController<
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>>
        extends SaveController<Entity, PostRequest> {

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            getMainService().delete(id);
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        }

        return Response.getOk();
    }
}
