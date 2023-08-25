package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);
}
