package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T extends BaseEntity, TId> {
    Optional<T> findById(TId id);
    List<T> findAll();
    T save(T t);
    void update(T t);
    void delete(T t);
}
