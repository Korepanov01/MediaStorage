package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    private byte[] data;

    public File(Long id, String name, byte[] data) {
        super(id);
        this.name = name;
        this.data = data;
    }
}
