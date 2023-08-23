package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.Optional;

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
        Optional<Entity> optionalEntity = getMainService().findById(id);
        if (optionalEntity.isEmpty())
            return Response.getEntityNotFound();

        Entity entity = optionalEntity.get();

        updateEntity(entity, request);

        try {
            getMainService().update(entity);
        } catch (EntityExistsException e) {
            return Response.getEntityAlreadyExists(e.getMessage());
        }

        return Response.getOk();
    }

    void updateEntity(@NonNull Entity entity, @NonNull PutRequest putRequest);
}
