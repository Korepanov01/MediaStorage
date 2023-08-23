package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface GetByIdController <
        Dto,
        Entity> {

    @GetMapping("/{id}")
    default ResponseEntity<?> get(
            @PathVariable
            Long id) {
        Optional<Entity> optionalEntity = getMainService().findById(id);
        if (optionalEntity.isEmpty())
            return Response.getEntityNotFound();
        return ResponseEntity.ok(convertToDto(optionalEntity.get()));
    }

    Dto convertToDto(Entity entity);

    CrudService<Entity> getMainService();
}
