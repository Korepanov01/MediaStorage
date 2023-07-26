package com.bftcom.mediastorage.model.entity;

public class FileType extends BaseEntity {
    private String name;

    public FileType(Long id, String type) {
        super(id);
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public FileType(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
