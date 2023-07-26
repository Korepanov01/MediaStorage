package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import lombok.NonNull;

import java.util.Optional;

public interface UserRepository
        extends ParametersSearchRepository<User, SearchStringParameters>,
        NameSearchRepository<User> {

    Optional<User> findByEmail(@NonNull String email);
}
