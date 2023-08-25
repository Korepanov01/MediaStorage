package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User> {

    List<User> findByNameContainsIgnoreCaseOrEmailContainsIgnoreCase(String nameSearchString, String emailSearchString, Pageable pageable);

    Optional<User> findByEmail(@NonNull String email);

    boolean existsByEmail(@NonNull String email);
}
