package com.bftcom.mediastorage.model.entity;

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
@Table(name = "file", schema = "public")
public class File implements Identical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType")
    @NotNull
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "file_type_id")
    private FileType fileType;

    public File(String name, String contentType, Long size, @NotNull byte[] data, FileType fileType) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.fileType = fileType;
    }
}
