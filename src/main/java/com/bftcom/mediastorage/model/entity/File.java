package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "file")
public class File extends BaseEntity {

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

    public File(Long Id, String name, String contentType, Long size, byte[] data) {
        super(Id);
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
