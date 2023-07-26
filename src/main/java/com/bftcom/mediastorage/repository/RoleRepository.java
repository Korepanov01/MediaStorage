package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

public interface RoleRepository extends ParametersSearchRepository<Role, SearchStringParameters>,
        NameSearchRepository<Role> {
}
