package com.bftcom.mediastorage.api.controller.base;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.request.DeleteEntityRequest;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class LinkObjectsController<
        Entity extends BaseEntity,
        PostRequest extends PostEntityRequest<Entity>,
        DeleteRequest extends DeleteEntityRequest>
        extends SaveController<Entity, PostRequest> {

    @DeleteMapping
    public abstract ResponseEntity<?> delete(@RequestBody @Valid DeleteRequest request);
}
