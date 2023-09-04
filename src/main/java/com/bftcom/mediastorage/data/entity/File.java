package com.bftcom.mediastorage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file", schema = "public",
    indexes = {
        @Index(name = "idx_file_file_type_id", columnList = "file_type_id")
    })
public class File implements Identical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @NotNull
    @Column(name = "size", nullable = false)
    private Long size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType")
    @NotNull
    @Column(name = "data", nullable = false)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "file_type_id", nullable = false)
    private FileType fileType;

    public File(String name, String contentType, Long size, @NotNull byte[] data, FileType fileType) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.fileType = fileType;
    }
}
