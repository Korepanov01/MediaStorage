package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParametersSearchRepository<Entity extends BaseEntity, Parameters>
        extends CrudRepository<Entity>{

    @Transactional(readOnly = true)
    List<Entity> findByParameters(@NonNull Parameters parameters);
}
