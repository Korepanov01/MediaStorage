package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media_type", schema = "public")
public class MediaType implements Identical {

    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    public MediaType(String name) {
        this.name = name;
    }
}
