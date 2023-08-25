package com.bftcom.mediastorage.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomJpaRepository<Entity> extends JpaRepository<Entity, Long> {

    Optional<Entity> findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);

}
