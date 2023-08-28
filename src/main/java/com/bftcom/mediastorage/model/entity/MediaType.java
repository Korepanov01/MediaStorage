package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media_type", schema = "public",
        indexes = {
            @Index(name = "uidx_media_type_name", columnList = "name", unique = true)
        }
)
public class MediaType implements Identical {

    public static final String IMAGE = "Изображение";
    public static final String VIDEO = "Видео";
    public static final String AUDIO = "Аудио";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;
}
