package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<Entity extends BaseEntity> {
    boolean isExists(@NonNull Long id);
    Optional<Entity> findById(@NonNull Long id);
    Entity save(@NonNull Entity entity);
    List<Entity> saveAll(@NonNull List<Entity> entities);
    void update(@NonNull Entity entity);
    void delete(@NonNull Entity entity);
}