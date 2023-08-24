package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Transactional
public interface DeleteController<Entity> {

    CrudService<Entity> getMainService();

    @DeleteMapping("/{id}")
    default ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            getMainService().delete(id);
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        } catch (IllegalOperationException e) {
            return Response.getBadRequest(e.getMessage());
        }

        return Response.getOk();
    }
}
