package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User> {
    Optional<User> findByEmail(@NonNull String email);

    boolean existsByEmail(@NonNull String email);
}
