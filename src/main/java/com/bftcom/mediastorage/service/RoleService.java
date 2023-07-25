package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IService<Role, SearchStringParameters> {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Role> findByParameters(SearchStringParameters parameters) {
        return repository.findByParameters(parameters);
    }

    @Override
    public Role save(Role role) throws EntityAlreadyExistsException {
        if (repository.findByName(role.getName()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        return repository.save(role);
    }

    @Override
    public void delete(Role role) throws EntityNotFoundException {
        if(repository.findById(role.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }

        repository.delete(role);
    }
}
