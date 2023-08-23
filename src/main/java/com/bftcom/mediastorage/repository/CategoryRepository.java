package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category> {

    @Transactional(readOnly = true)
    List<Category> findByParentCategoryId(@NonNull Long parentCategoryId);

    @Transactional(readOnly = true)
    Category findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);
}
