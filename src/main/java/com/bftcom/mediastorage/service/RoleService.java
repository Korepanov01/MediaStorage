package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService extends CrudService<Role> {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(@NonNull String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public boolean isSameEntityExists(@NonNull Role role) {
        return roleRepository.existsByName(role.getName());
    }

    @Override
    protected CrudRepository<Role> getMainRepository() {
        return roleRepository;
    }
}
