package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User> {

    @Query("SELECT u FROM User u \n" +
            "WHERE :searchString IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :searchString, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<User> findByParameters(
            @Param("searchString") String searchString,
            Pageable pageable
    );

    Optional<User> findByEmail(@NonNull String email);

    boolean existsByEmail(@NonNull String email);
}
