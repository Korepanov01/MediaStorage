package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaType extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String name;

    public MediaType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
