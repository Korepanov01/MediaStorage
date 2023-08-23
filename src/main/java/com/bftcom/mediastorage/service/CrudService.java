package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class CrudService<Entity> {

    @Transactional(readOnly = true)
    public Optional<Entity> findById(@NonNull Long id) {
        return getMainRepository().findById(id);
    }

    @Transactional(readOnly = true)
    public List<Entity> findAll() {
        return getMainRepository().findAll();
    }

    @Transactional
    public void save(@NonNull Entity entity) throws EntityExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityExistsException();
        }
        getMainRepository().save(entity);
    }

    @Transactional
    public void update(@NonNull Entity entity) throws EntityExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityExistsException();
        }
        getMainRepository().update(entity);
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

    @Transactional
    public abstract boolean isSameEntityExists(@NonNull Entity entity);
}
