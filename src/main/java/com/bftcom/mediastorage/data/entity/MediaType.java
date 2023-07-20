package com.bftcom.mediastorage.data.entity;

public class MediaType extends BaseEntity {

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
