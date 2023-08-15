package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.searchparameters.RoleSearchParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService extends ParameterSearchService<Role, RoleSearchParameters> {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(@NonNull String name) {
        return roleRepository.findByName(name);    }

    @Override
    protected boolean isSameEntityExists(@NonNull Role role) {
        return roleRepository.findByName(role.getName()).isPresent();
    }

    @Override
    protected ParametersSearchRepository<Role, RoleSearchParameters> getMainRepository() {
        return roleRepository;
    }
}
