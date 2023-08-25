package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;

import java.util.List;

public abstract class ParameterSearchService<Entity, Parameters> extends CrudService<Entity> {

    public abstract List<Entity> findByParameters(Parameters parameters) throws EntityNotFoundException;
}
