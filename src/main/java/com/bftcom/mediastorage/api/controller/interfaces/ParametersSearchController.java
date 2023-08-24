package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public interface ParametersSearchController <
        ListItemDto,
        Entity,
        SearchParameters> {

    @GetMapping
    default List<ListItemDto> get(
            @Valid
            SearchParameters parameters) {
        return getMainService()
                .findByParameters(parameters)
                .stream()
                .map(this::convertToListItemDto)
                .collect(Collectors.toList());
    }

    ListItemDto convertToListItemDto(@NonNull Entity entity);

    ParameterSearchService<Entity, SearchParameters> getMainService();
}
