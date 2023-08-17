package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MediaTypeRepository extends ParametersSearchRepository<MediaType, SearchStringParameters> {
    @Transactional(readOnly = true)
    Optional<MediaType> findByName(@NonNull String name);
}
