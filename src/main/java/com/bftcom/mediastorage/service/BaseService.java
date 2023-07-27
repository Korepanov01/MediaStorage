package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public abstract class BaseService<Entity extends BaseEntity, SearchParameters extends PagingParameters>
        implements Service<Entity, SearchParameters> {

    protected abstract ParametersSearchRepository<Entity, SearchParameters> getMainRepository();

    @Override
    public List<Entity> findByParameters(@NonNull SearchParameters parameters) {
        return getMainRepository().findByParameters(parameters);
    }

    @Override
    public Entity save(@NonNull Entity entity) throws EntityAlreadyExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityAlreadyExistsException();
        }
        return getMainRepository().save(entity);
    }

    @Override
    public void delete(@NonNull Long id) throws EntityNotFoundException {
        Optional<Entity> optionalEntity = getMainRepository().findById(id);

        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        getMainRepository().delete(optionalEntity.get());
    }

    protected abstract boolean isSameEntityExists(@NonNull Entity entity);
}