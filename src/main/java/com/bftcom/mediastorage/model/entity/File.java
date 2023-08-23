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
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "size")
    private Long size;

    @Lob
    @NotNull
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "file_type_id")
    private FileType fileType;

    @ManyToMany(mappedBy = "files")
    private Set<Media> media = new HashSet<>();

    public File(String name, String contentType, Long size, @NotNull byte[] data, FileType fileType) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.fileType = fileType;
    }
}
