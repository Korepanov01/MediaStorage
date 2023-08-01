package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteController<Entity extends BaseEntity> {

    CrudService<Entity> getMainService();

    @DeleteMapping("/{id}")
    default ResponseEntity<?> delete(
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
