package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;

import java.util.Optional;

public interface UserRepository extends ParametersSearchRepository<User, SearchStringParameters> {

    Optional<User> findByEmail(String email);
}
