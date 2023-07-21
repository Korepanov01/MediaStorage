package com.bftcom.mediastorage.model.parameters;

import java.util.List;

public class MediaSearchParameters extends SearchStringParameters {
    private List<Long> tagIds;

    private Long categoryId;

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
