package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String name;
    private String passwordHash;
    private String email;

    public User(Long id, String name, String passwordHash, String email) {
        super(id);
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }
}
