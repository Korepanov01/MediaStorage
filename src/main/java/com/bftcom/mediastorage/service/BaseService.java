//package com.bftcom.mediastorage.service;
//
//import com.bftcom.mediastorage.exception.EntityNotFoundException;
//import com.bftcom.mediastorage.model.entity.BaseEntity;
//import com.bftcom.mediastorage.repository.CrudRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public abstract class BaseService<T extends BaseEntity>
//        implements IService<T> {
//
//    private CrudRepository<T> repository;
//
//    @Autowired
//    public void setRepository(CrudRepository<T> repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public boolean isExists(Long id) {
//        return repository.findById(id).isPresent();
//    }
//
//    @Override
//    public Optional<T> findById(Long id) {
//        return repository.findById(id);
//    }
//
//    @Override
//    public T save(T entity) {
//        repository.save(entity);
//        return entity;
//    }
//
//    @Override
//    public void delete(T entity) throws EntityNotFoundException {
//        if(!isExists(entity.getId())) {
//            throw new EntityNotFoundException();
//        }
//
//        repository.delete(entity);
//    }
//}
