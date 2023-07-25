package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;

import java.util.List;

public interface ParametersSearchRepository<Entity extends BaseEntity, Parameters extends PagingParameters>
        extends CrudRepository<Entity>{

    List<Entity> findByParameters(Parameters parameters);
}
