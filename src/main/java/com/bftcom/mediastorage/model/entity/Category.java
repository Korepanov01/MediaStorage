package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    private Long parentCategoryId;

    public Category(Long id, String name, Long parentCategoryId) {
        super(id);
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }
}
