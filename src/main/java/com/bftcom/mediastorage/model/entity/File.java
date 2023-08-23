package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file", schema = "public")
public class File implements Identical {

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
    private Set<Media> media = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "file_type_id")
    private FileType fileType;

    public File(String name, String contentType, Long size, @NotNull byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
