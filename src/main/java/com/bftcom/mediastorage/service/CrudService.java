package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class CrudService<Entity extends BaseEntity> {

    public Optional<Entity> findById(@NonNull Long id) {
        return getMainRepository().findById(id);
    }

    @Transactional
    public void save(@NonNull Entity entity) throws EntityAlreadyExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityAlreadyExistsException();
        }
        getMainRepository().save(entity);
    }

    @Transactional
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
