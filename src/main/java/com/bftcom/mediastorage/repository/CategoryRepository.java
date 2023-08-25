package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.NonNull;

import java.util.List;

public interface CategoryRepository extends CustomJpaRepository<Category> {

    List<Category> findByParentCategoryId(@NonNull Long parentCategoryId);
}
