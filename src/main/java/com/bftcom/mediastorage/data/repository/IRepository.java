package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends BaseModel, TId> {
    Optional<T> findById(TId id);
    List<T> findAll();
    T save(T t);
    void update(T t);
    void delete(T t);
}
