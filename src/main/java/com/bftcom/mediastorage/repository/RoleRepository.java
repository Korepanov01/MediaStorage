package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role> {

    Optional<Role> findByName(String name);

    List<Role> findByParameters(SearchStringParameters parameters);
}
