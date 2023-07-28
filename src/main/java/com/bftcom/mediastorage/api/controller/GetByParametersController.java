package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GetByParametersController<
        ListItemDto extends BaseDto,
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>,
        SearchParameters extends PagingParameters>
        extends CrudController<Entity, PostRequest> {

    @GetMapping
    public List<ListItemDto> get(
            SearchParameters parameters) {
        List<Entity> entities = getMainService().findByParameters(parameters);
        return entities
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    protected abstract ListItemDto convertToDto(Entity entity);

    protected abstract ParameterSearchService<Entity, SearchParameters> getMainService();
}
