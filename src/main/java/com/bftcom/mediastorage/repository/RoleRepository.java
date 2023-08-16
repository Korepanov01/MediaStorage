package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends ParametersSearchRepository<Role, SearchStringParameters> {

    @Transactional(readOnly = true)
    Optional<Role> findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);

    @Transactional(readOnly = true)
    List<Role> findByParameters(@NonNull SearchStringParameters parameters);

    @Transactional(readOnly = true)
    List<Role> findByUserId(Long userId);
}
