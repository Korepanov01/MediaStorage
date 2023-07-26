package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;

import java.util.Optional;

public interface NameSearchRepository<Entity extends BaseEntity>
        extends CrudRepository<Entity> {
    Optional<Entity> findByName(String name);
}
