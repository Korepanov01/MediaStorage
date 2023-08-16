package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.UserRole;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole> {

    @Transactional(readOnly = true)
    Optional<UserRole> findByUserAndRole(@NonNull Long userId, @NonNull Long roleId);

    @Transactional(readOnly = true)
    boolean isExists(@NonNull Long userId, @NonNull Long roleId);
}
