package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CrudRepository<Entity extends BaseEntity> {
    boolean existsById(@NonNull Long id);
    Optional<Entity> findById(@NonNull Long id);
    @Transactional
    void save(@NonNull Entity entity);
    void update(@NonNull Entity entity);
    void delete(@NonNull Entity entity);
}
