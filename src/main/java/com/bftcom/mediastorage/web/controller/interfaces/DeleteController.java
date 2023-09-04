package com.bftcom.mediastorage.web.controller.interfaces;

import com.bftcom.mediastorage.web.Responses;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteController<Entity> {

    CrudService<Entity> getMainService();

    @Transactional
    @DeleteMapping("/{id}")
    default ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            getMainService().delete(id);
        } catch (EntityNotFoundException e) {
            return Responses.notFound(e.getMessage());
        } catch (IllegalOperationException e) {
            return Responses.badRequest(e.getMessage());
        }

        return Responses.OK;
    }
}
