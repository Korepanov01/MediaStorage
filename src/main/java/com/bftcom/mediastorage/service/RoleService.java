package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService extends ParameterSearchService<Role, SearchStringParameters> {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(@NonNull String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findByUserId(@NonNull Long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public boolean isSameEntityExists(@NonNull Role role) {
        return roleRepository.existsByName(role.getName());
    }

    @Override
    protected ParametersSearchRepository<Role, SearchStringParameters> getMainRepository() {
        return roleRepository;
    }
}
