package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @NotBlank
    @Size(max = 200)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Media> media;

    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }
}
