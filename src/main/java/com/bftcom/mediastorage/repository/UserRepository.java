package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(@NonNull String name);

    User findByEmail(@NonNull String email);

    boolean existsByName(@NonNull String name);

    boolean existsByEmail(@NonNull String email);
}
