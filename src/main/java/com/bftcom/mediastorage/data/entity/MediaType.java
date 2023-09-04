package com.bftcom.mediastorage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media_type", schema = "public",
        indexes = {
            @Index(name = "uidx_media_type_name", columnList = "name", unique = true)
        }
)
public class MediaType extends BaseEntity {

    public static final String IMAGE = "Изображение";
    public static final String VIDEO = "Видео";
    public static final String AUDIO = "Аудио";

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;
}
