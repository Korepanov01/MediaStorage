package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseEntity {
    @NotBlank
    @Size(max = 200)
    private String name;
    @NotBlank
    @Size(max = 50)
    private String contentType;
    @NotNull
    private Long size;
    @NotNull
    private byte[] data;

    public File(Long Id, String name, String contentType, Long size, byte[] data) {
        super(Id);
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
