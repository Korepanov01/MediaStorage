package com.bftcom.mediastorage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag", schema = "public",
        indexes = {
            @Index(name = "uidx_tag_name", columnList = "name", unique = true)
        }
)
public class Tag extends BaseEntity {

    @NotBlank
    @Size(max = 200)
    @Column(name = "name", nullable = false)
    private String name;
}
