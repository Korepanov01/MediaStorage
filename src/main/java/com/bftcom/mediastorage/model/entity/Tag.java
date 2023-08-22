package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag", schema = "public")
public class Tag {

    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Media> media;
}
