package com.bftcom.mediastorage.data.model;

public abstract class BaseModel {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
