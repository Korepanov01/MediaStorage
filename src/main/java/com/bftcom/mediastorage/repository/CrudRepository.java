package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends BaseEntity> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T t);
    void update(T t);
    void delete(T t);
}
