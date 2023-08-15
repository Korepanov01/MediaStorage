package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileType extends BaseEntity {

    public static final String THUMBNAIL = "Превью";
    public static final String MAIN = "Основной";

    @NotBlank
    @Size(max = 100)
    private String name;

    public FileType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
