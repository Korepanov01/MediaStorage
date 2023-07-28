package com.bftcom.mediastorage.api.controller.base;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class SaveController<
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>> {

    protected abstract CrudService<Entity> getMainService();

    @PostMapping
    public ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostRequest request) {
        Entity entity = request.covertToEntity();

        try {
            getMainService().save(entity);
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(entity);
        return ResponseEntity.ok(response);
    }
}
