package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role> {

    @Transactional(readOnly = true)
    Optional<Role> findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);
}
