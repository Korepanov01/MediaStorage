package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<Entity> {

    public Optional<Entity> findById(@NonNull Long id) {
        return getMainRepository().findById(id);
    }

    public Optional<Entity> findByName(@NonNull String name) {
        return getMainRepository().findByName(name);
    }

    public boolean existsByName(@NonNull String name) {
        return getMainRepository().existsByName(name);
    }

    public List<Entity> findAll() {
        return getMainRepository().findAll();
    }

    public void save(@NonNull Entity entity) throws EntityExistsException {
        if (isSameEntityExists(entity)) {
            throw new EntityExistsException("Запись уже существует");
        }
        getMainRepository().save(entity);
    }

    public void delete(@NonNull Long id) throws EntityNotFoundException, IllegalOperationException {
        Optional<Entity> optionalEntity = getMainRepository().findById(id);

        getMainRepository().delete(optionalEntity
                .orElseThrow(() -> new EntityNotFoundException("Запись не найдена")));
    }

    protected abstract CustomJpaRepository<Entity> getMainRepository();

    public abstract boolean isSameEntityExists(@NonNull Entity entity);
}
