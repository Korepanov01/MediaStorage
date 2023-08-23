package com.bftcom.mediastorage.repository;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudRepository<Entity> {

    @Transactional(readOnly = true)
    Entity findById(@NonNull Long id);

    @Transactional(readOnly = true)
    List<Entity> findAll();

    @Transactional
    void save(@NonNull Entity entity);

    @Transactional
    void update(@NonNull Entity entity);

    @Transactional
    void delete(@NonNull Entity entity);
}
