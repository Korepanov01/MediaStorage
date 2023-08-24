package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Transactional
public interface GetByIdController <
        Dto,
        Entity> {

    @GetMapping("/{id}")
    default ResponseEntity<?> get(
            @PathVariable
            Long id) {
        Entity entity = getMainService().findById(id);
        if (entity == null)
            return Response.getEntityNotFound();
        return ResponseEntity.ok(convertToDto(entity));
    }

    Dto convertToDto(@NonNull Entity entity);

    CrudService<Entity> getMainService();
}
