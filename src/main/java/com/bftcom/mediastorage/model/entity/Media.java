package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Media extends BaseEntity {
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private Long mediaTypeId;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
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
