package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class ParameterSearchService<Entity, SearchParameters>
        extends CrudService<Entity>{

    @Transactional(readOnly=true)
    public List<Entity> findByParameters(@NonNull SearchParameters parameters) {
        return getMainRepository().findByParameters(parameters);
    }

    @Override
    protected abstract ParametersSearchRepository<Entity, SearchParameters> getMainRepository();
}