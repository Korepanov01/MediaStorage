package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    public User(Long id, String name, String passwordHash, String email) {
        super(id);
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }
}
