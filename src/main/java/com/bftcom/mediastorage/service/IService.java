package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import lombok.NonNull;

import java.util.List;

public interface IService<Entity extends BaseEntity, SearchParameters extends PagingParameters> {

    List<Entity> findByParameters(@NonNull SearchParameters parameters);

    Entity save(@NonNull Entity entity) throws EntityAlreadyExistsException;

    void delete(@NonNull Long id) throws EntityNotFoundException;
}