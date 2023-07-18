package com.bftcom.mediastorage.data.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRepository<T, TId> {
    Optional<T> findById(TId id);
    List<T> findAll();
    T save(T t);
    void update(T t);
    void delete(T t);
}
