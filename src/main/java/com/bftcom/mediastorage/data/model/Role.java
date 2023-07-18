package com.bftcom.mediastorage.data.model;

public class Role extends BaseModel {

    private String name;

    public Role(Long id, String name) {
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
