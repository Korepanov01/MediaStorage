package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
