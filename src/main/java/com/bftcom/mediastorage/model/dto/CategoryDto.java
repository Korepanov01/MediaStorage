package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CategoryDto {
    private final Long id;
    private String name;
    private Long parentCategoryId;

    public CategoryDto(@NonNull Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getParentCategory() != null ? category.getParentCategory().getId() : 0
        );
    }
}
