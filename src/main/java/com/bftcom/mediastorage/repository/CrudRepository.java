package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<Entity extends BaseEntity> {

    @Transactional(readOnly = true)
    Optional<Entity> findById(@NonNull Long id);

    @Transactional(readOnly = true)
    List<Entity> findAll();

    @Transactional
    void save(@NonNull Entity entity);

    @Transactional
    void update(@NonNull Entity entity);

    @Transactional
    void delete(@NonNull Entity entity);
}
