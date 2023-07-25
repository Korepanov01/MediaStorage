package com.bftcom.mediastorage.model.entity;

public class User extends BaseEntity {

    private String name;

    private String passwordHash;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Long id, String name, String passwordHash, String email) {
        super(id);
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public User(String name, String passwordHash, String email) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
