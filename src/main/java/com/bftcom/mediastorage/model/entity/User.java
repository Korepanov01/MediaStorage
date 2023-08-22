package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String passwordHash;

    @NotBlank
    @Size(max = 500)
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Media> medias;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String name, String passwordHash, String email) {
        super(id);
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }
}
