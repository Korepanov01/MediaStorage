package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

public interface PutController <
        Entity,
        PutRequest> {

    CrudService<Entity> getMainService();

    @Transactional
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

        try {
            updateEntity(optionalEntity.get(), request);
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        } catch (EntityExistsException | IllegalOperationException e) {
            return Response.getBadRequest(e.getMessage());
        }

        return Response.getOk();
    }

    void updateEntity(@NonNull Entity entity, @NonNull PutRequest putRequest) throws EntityNotFoundException, EntityExistsException, IllegalOperationException;
}
