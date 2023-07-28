package com.bftcom.mediastorage.service.interfaces;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CrudService<Entity extends BaseEntity, SearchParameters extends PagingParameters>
        extends SaveDeleteService<Entity> {

    Optional<Entity> findById(@NonNull Long id);

    List<Entity> findByParameters(@NonNull SearchParameters parameters);
}