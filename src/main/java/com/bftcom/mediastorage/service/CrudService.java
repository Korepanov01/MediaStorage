package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class CrudService<Entity extends BaseEntity, SearchParameters extends PagingParameters>
        extends SaveDeleteService<Entity>
        implements com.bftcom.mediastorage.service.interfaces.CrudService<Entity, SearchParameters> {

    @Override
    public Optional<Entity> findById(@NonNull Long id) {
        return getMainRepository().findById(id);
    }

    @Override
    public List<Entity> findByParameters(@NonNull SearchParameters parameters) {
        return getMainRepository().findByParameters(parameters);
    }

    @Override
    protected abstract ParametersSearchRepository<Entity, SearchParameters> getMainRepository();
}