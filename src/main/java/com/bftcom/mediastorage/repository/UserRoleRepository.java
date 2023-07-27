package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.UserRole;
import lombok.NonNull;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole> {

    Optional<UserRole> findByUserAndRole(@NonNull Long userId, @NonNull Long roleId);

    boolean isExists(@NonNull Long userId, @NonNull Long roleId);
}
