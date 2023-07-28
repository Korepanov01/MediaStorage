package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService extends CrudService<Role, SearchStringParameters> {

    private final RoleRepository roleRepository;

    public List<Role> getUserRoles(@NonNull Long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Role role) {
        return roleRepository.findByName(role.getName()).isPresent();
    }

    @Override
    protected ParametersSearchRepository<Role, SearchStringParameters> getMainRepository() {
        return roleRepository;
    }
}
