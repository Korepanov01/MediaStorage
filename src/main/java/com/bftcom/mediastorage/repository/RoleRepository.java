package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.Optional;

public interface RoleRepository extends ParametersSearchRepository<Role, SearchStringParameters> {

    Optional<Role> findByName(String name);
}
