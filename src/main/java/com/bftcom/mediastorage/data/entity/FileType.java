package com.bftcom.mediastorage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_type", schema = "public",
    indexes = {
        @Index(name = "uidx_file_type_name", columnList = "name", unique = true)
    })
public class FileType extends BaseEntity {

    public static final String THUMBNAIL = "Превью";
    public static final String MAIN = "Основной";

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;
}
