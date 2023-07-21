package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends BaseEntity, TId> {
    Optional<T> findById(TId id);
    List<T> findAll();
    T save(T t);
    void update(T t);
    void delete(T t);
}