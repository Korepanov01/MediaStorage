package com.bftcom.mediastorage.repository;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParametersSearchRepository<Entity, Parameters>
        extends CrudRepository<Entity>{

    @Transactional(readOnly = true)
    List<Entity> findByParameters(@NonNull Parameters parameters);
}
