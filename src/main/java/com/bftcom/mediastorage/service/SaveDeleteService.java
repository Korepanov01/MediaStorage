package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;

import java.util.Optional;

public abstract class SaveDeleteService <Entity extends BaseEntity>
        implements com.bftcom.mediastorage.service.interfaces.SaveDeleteService<Entity> {

    @Override
    public void save(@NonNull Entity entity) throws EntityAlreadyExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityAlreadyExistsException();
        }
        getMainRepository().save(entity);
    }

    @Override
    public void delete(@NonNull Long id) throws EntityNotFoundException {
        Optional<Entity> optionalEntity = getMainRepository().findById(id);

        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        getMainRepository().delete(optionalEntity.get());
    }

    protected abstract CrudRepository<Entity> getMainRepository();

    protected abstract boolean isSameEntityExists(@NonNull Entity entity);
}
