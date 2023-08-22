package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file", schema = "public")
public class File {

    @Id
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String contentType;

    @NotNull
    private Long size;

    @Lob
    @NotNull
    private byte[] data;

    @ManyToMany(mappedBy = "files")
    private Set<Media> media;
}
