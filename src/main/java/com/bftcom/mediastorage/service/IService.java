package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.PagingParameters;

import java.util.List;

public interface IService<Entity extends BaseEntity> {

    Entity save(Entity t) throws EntityAlreadyExistsException;

    void delete(Entity t) throws EntityNotFoundException;
}