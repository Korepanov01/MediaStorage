package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileType extends BaseEntity {
    @NotNull
    private String name;

    public FileType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
