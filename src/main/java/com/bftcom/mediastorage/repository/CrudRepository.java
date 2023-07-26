package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;

import java.util.Optional;

public interface CrudRepository<Entity extends BaseEntity> {
    Optional<Entity> findById(@NonNull Long id);
    Entity save(@NonNull Entity entity);
    void update(@NonNull Entity entity);
    void delete(@NonNull Entity entity);
}
