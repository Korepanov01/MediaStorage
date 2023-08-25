package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService extends CrudService<Role> {

    private final RoleRepository roleRepository;

    @Override
    public boolean isSameEntityExists(@NonNull Role role) {
        return roleRepository.existsByName(role.getName());
    }

    @Override
    protected CustomJpaRepository<Role> getMainRepository() {
        return roleRepository;
    }
}
