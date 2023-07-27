package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CrudService<Entity extends BaseEntity, SearchParameters extends PagingParameters> {

    Optional<Entity> findById(@NonNull Long id);

    List<Entity> findByParameters(@NonNull SearchParameters parameters);

    void save(@NonNull Entity entity) throws EntityAlreadyExistsException;

    void delete(@NonNull Long id) throws EntityNotFoundException;
}