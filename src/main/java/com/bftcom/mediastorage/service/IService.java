package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;

import java.util.List;

public interface IService<Entity extends BaseEntity, SearchParameters extends PagingParameters> {

    List<Entity> findByParameters(SearchParameters parameters);

    Entity save(Entity entity) throws EntityAlreadyExistsException;

    void delete(Long id) throws EntityNotFoundException;
}