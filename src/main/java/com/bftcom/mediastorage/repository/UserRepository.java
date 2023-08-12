package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;

import java.util.Optional;

public interface UserRepository
        extends ParametersSearchRepository<User, SearchStringParameters>,
        NameSearchRepository<User> {

    Optional<User> findByEmail(@NonNull String email);
    boolean existsByName(@NonNull String name);
    boolean existsByEmail(@NonNull String email);

    void updateEmail(@NonNull String email);

    void updateName(@NonNull String name);
}
