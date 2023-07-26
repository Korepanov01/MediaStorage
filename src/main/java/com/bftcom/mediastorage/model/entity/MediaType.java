package com.bftcom.mediastorage.model.entity;

public class MediaType extends BaseEntity {

    public MediaType(String name) {
        this.name = name;
    }

    private String name;

    public MediaType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
