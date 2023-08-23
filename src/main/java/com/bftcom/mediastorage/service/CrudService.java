package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public abstract class CrudService<Entity> {

    @Transactional(readOnly = true)
    public Entity findById(@NonNull Long id) {
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
        Entity entity = getMainRepository().findById(id);

        if (entity == null)
            throw new EntityNotFoundException("Запись не найдена");

        getMainRepository().delete(entity);
    }

    protected abstract CrudRepository<Entity> getMainRepository();

    @Transactional
    public abstract boolean isSameEntityExists(@NonNull Entity entity);
}
