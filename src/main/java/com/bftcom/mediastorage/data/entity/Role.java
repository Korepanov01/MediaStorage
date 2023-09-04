package com.bftcom.mediastorage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role", schema = "public",
        indexes = {
                @Index(name = "uidx_role_name", columnList = "name", unique = true)
        })
public class Role extends BaseEntity {

    public static final String ADMIN = "ADMIN";
    public static final String SUPER_ADMIN = "SUPER_ADMIN";

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;
}
