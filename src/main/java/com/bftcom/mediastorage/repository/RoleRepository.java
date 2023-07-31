package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.RoleSearchParameters;

import java.util.List;

public interface RoleRepository extends ParametersSearchRepository<Role, RoleSearchParameters>,
        NameSearchRepository<Role> {

    List<Role> findByUserId(Long userId);
}
