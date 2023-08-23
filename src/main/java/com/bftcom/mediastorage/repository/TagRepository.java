package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagRepository extends ParametersSearchRepository<Tag, SearchStringParameters> {

    @Transactional(readOnly = true)
    Tag findByName(@NonNull String name);

    @Transactional(readOnly = true)
    List<Tag> findByParameters(@NonNull SearchStringParameters parameters);
}
