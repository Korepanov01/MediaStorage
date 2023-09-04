package com.bftcom.mediastorage.data.entity;

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
@Table(name = "tag", schema = "public",
        indexes = {
            @Index(name = "uidx_tag_name", columnList = "name", unique = true)
        }
)
public class Tag implements Identical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "name", nullable = false)
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
