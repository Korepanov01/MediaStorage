package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;

import java.util.Optional;

public interface CrudRepository<Entity extends BaseEntity> {
    Optional<Entity> findById(Long id);
    Entity save(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
