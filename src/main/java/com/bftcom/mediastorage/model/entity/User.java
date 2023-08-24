package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User implements Identical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 256)
    @Column(name = "password_hash")
    private String passwordHash;

    @NotBlank
    @Size(max = 500)
    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Media> medias = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public User(String name, String passwordHash, String email) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }
}
