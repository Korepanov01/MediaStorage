package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, SearchStringParameters> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(@NonNull RoleRepository repository) {
        this.roleRepository = repository;
    }

    @Override
    protected boolean isEntityExists(@NonNull Role role) {
        return roleRepository.findByName(role.getName()).isPresent();
    }

    @Override
    protected ParametersSearchRepository<Role, SearchStringParameters> getMainRepository() {
        return roleRepository;
    }
}
