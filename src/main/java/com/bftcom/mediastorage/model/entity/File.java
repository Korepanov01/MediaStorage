package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseEntity {
    private String name;
    private byte[] data;

    public File(Long id, String name, byte[] data) {
        super(id);
        this.name = name;
        this.data = data;
    }
}
