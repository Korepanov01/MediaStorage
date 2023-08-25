package com.bftcom.mediastorage.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomJpaRepository<Entity> extends JpaRepository<Entity, Long> {

    Optional<Entity> findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);

}
