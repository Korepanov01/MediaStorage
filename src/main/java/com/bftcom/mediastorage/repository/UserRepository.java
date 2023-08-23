package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends ParametersSearchRepository<User, SearchStringParameters> {

    @Transactional(readOnly = true)
    User findByName(@NonNull String name);

    @Transactional(readOnly = true)
    User findByEmail(@NonNull String email);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByEmail(@NonNull String email);
}
