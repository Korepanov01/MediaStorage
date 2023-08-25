package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

public interface ParametersSearchController <
        ListItemDto,
        Entity,
        SearchParameters> {

    @GetMapping
    default ResponseEntity<?> get(
            @Valid
            SearchParameters parameters) {
        try {
            return ResponseEntity.ok(getMainService()
                    .findByParameters(parameters)
                    .stream()
                    .map(this::convertToListItemDto)
                    .collect(Collectors.toList()));
        } catch (EntityNotFoundException e) {
            return Response.getEntityNotFound(e.getMessage());
        }
    }

    ListItemDto convertToListItemDto(@NonNull Entity entity);

    ParameterSearchService<Entity, SearchParameters> getMainService();
}
