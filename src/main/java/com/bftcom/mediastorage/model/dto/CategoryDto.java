package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends BaseDto {
    private final Long id;
    private String name;
    private Long parentCategoryId;

    public CategoryDto(Category category) {
        this(category.getId(), category.getName(), category.getParentCategoryId());
    }
}
