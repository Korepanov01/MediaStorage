package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends ParametersSearchRepository<Tag, SearchStringParameters> {

    @Transactional(readOnly = true)
    Optional<Tag> findByName(@NonNull String name);

    @Transactional(readOnly = true)
    List<Tag> findByParameters(@NonNull SearchStringParameters parameters);

    @Transactional(readOnly = true)
    List<Tag> findByMediaId(@NonNull Long mediaId);
}
