package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Responses;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface GetByIdController <
        Dto,
        Entity> {

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    default ResponseEntity<?> get(
            @PathVariable
            Long id) {
        Optional<Entity> optionalEntity = getMainService().findById(id);
        if (optionalEntity.isEmpty())
            return Responses.NOT_FOUND;
        return ResponseEntity.ok(convertToDto(optionalEntity.get()));
    }

    Dto convertToDto(@NonNull Entity entity);

    CrudService<Entity> getMainService();
}
