package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseService<Entity extends BaseEntity, SearchParameters extends PagingParameters>
        implements IService<Entity, SearchParameters> {

    protected abstract ParametersSearchRepository<Entity, SearchParameters> getMainRepository();

    @Override
    public List<Entity> findByParameters(SearchParameters parameters) {
        return getMainRepository().findByParameters(parameters);
    }

    @Override
    public Entity save(Entity entity) throws EntityAlreadyExistsException {
        if (isEntityExists(entity)) {
            throw new EntityAlreadyExistsException();
        }
        return getMainRepository().save(entity);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Entity> optionalEntity = getMainRepository().findById(id);

        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        getMainRepository().delete(optionalEntity.get());
    }

    public Optional<Entity> findById(Long id) {
        return getMainRepository().findById(id);
    }

    protected abstract boolean isEntityExists(Entity entity);
}