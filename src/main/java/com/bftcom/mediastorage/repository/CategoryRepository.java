package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.searchparameters.CategorySearchParameters;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends ParametersSearchRepository<Category, CategorySearchParameters> {

    @Transactional(readOnly = true)
    Optional<Category> findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);
}
