package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryRepository extends CustomJpaRepository<Category> {

    @Query(nativeQuery = true,
            value = "WITH RECURSIVE subcategories AS ( \n" +
                    "   SELECT id, parent_category_id FROM category WHERE id = :categoryId \n" +
                    "   UNION \n" +
                    "   SELECT c.id, c.parent_category_id FROM category c \n" +
                    "   INNER JOIN subcategories s ON c.parent_category_id = s.id \n" +
                    ") \n" +
                    "SELECT id FROM subcategories")
    List<Long> findAllSubcategoryIds(@Param("categoryId") @Nullable Long categoryId);

    List<Category> findByParentCategoryId(@Nullable Long parentCategoryId);
}
