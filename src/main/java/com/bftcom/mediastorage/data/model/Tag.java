package com.bftcom.mediastorage.data.model;

public class Tag extends BaseModel {

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Long id, String name) {
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
