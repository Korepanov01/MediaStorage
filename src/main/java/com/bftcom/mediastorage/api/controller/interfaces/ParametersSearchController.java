package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.service.ParameterSearchService;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public interface ParametersSearchController <
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
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

    ListItemDto convertToListItemDto(Entity entity);

    ParameterSearchService<Entity, SearchParameters> getMainService();
}
