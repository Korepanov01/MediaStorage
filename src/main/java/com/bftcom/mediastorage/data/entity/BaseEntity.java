package com.bftcom.mediastorage.data.entity;

public abstract class BaseEntity {
    private Long Id;

    public BaseEntity() {}

    public BaseEntity(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
