package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.api.request.ToEntityConvertable;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface PutController <
        Entity extends BaseEntity,
        PutRequest extends ToEntityConvertable<Entity>> {

    CrudService<Entity> getMainService();

    @PutMapping("/{id}")
    default ResponseEntity<?> delete(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            PutRequest request) {
        Entity entity = request.covertToEntity();
        entity.setId(id);

        try {
            getMainService().update(entity);
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return Response.getOk();
    }
}
