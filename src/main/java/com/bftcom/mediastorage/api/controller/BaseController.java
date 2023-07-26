package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.dto.BaseDto;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.BaseService;
import com.bftcom.mediastorage.api.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public abstract class BaseController<
        Dto extends BaseDto,
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>,
        SearchParameters extends PagingParameters> {

    @GetMapping
    public List<Dto> get(
            SearchParameters parameters) {
        List<Entity> entities = getMainService().findByParameters(parameters);
        return entities
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    protected abstract Dto convertToDto(Entity entity);

    protected abstract BaseService<Entity, SearchParameters> getMainService();

    @PostMapping
    public ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostRequest request) {
        Entity entity = request.covertToEntity();

        try {
            getMainService().save(entity);
        } catch (EntityAlreadyExistsException exception) {
            return Response.ENTITY_ALREADY_EXISTS;
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(entity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            getMainService().delete(id);
        } catch (EntityNotFoundException exception) {
            return Response.ENTITY_NOT_FOUND;
        }

        return Response.OK;
    }
}
