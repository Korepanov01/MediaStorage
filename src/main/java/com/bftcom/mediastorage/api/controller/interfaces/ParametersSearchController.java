package com.bftcom.mediastorage.api.controller.interfaces;

import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public interface ParametersSearchController <
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
        SearchParameters extends PagingParameters> {

    @GetMapping
    public default List<ListItemDto> get(
            SearchParameters parameters) {
        return getMainService()
                .findByParameters(parameters)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    ListItemDto convertToDto(Entity entity);

    ParameterSearchService<Entity, SearchParameters> getMainService();
}
