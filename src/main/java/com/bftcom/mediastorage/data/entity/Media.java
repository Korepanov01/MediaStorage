package com.bftcom.mediastorage.data.entity;

import java.time.LocalDateTime;

public class Media extends BaseEntity {

    private Long userId;

    private Long categoryId;

    private String name;

    private String description;

    private Long mediaTypeId;

    private LocalDateTime createdAt;

    private LocalDateTime editedAt;

    public Media(Long id, Long userId, Long categoryId, String name, String description, Long mediaTypeId, LocalDateTime createdAt, LocalDateTime editedAt) {
        super(id);
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.mediaTypeId = mediaTypeId;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(Long mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
    }
}
