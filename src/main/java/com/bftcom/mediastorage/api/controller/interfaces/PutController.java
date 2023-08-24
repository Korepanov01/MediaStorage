package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Transactional
public interface PutController <
        Entity,
        PutRequest> {

    CrudService<Entity> getMainService();

    @PutMapping("/{id}")
    default ResponseEntity<?> put(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            PutRequest request) {
        Entity entity = getMainService().findById(id);
        if (entity == null)
            return Response.getEntityNotFound();

        try {
            updateEntity(entity, request);
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        }

        try {
            getMainService().update(entity);
        } catch (EntityExistsException e) {
            return Response.getEntityAlreadyExists(e.getMessage());
        }

        return Response.getOk();
    }

    void updateEntity(@NonNull Entity entity, @NonNull PutRequest putRequest) throws EntityNotFoundException;
}
