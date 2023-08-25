package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentCategoryId(@NonNull Long parentCategoryId);

    Category findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);
}
