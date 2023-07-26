package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileType extends BaseEntity {
    private String name;

    public FileType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
