package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaType extends BaseEntity {
    private String name;

    public MediaType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
