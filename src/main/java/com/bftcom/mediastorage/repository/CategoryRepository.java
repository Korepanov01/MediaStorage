package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryRepository extends CustomJpaRepository<Category> {

    List<Category> findByParentCategoryId(@Nullable Long parentCategoryId);
}
