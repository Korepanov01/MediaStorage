package com.bftcom.mediastorage.data.model;

public class Category extends BaseModel {
    public Category(Long id, String name, Long parentCategoryId) {
        super(id);
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    private String name;

    private Long parentCategoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
