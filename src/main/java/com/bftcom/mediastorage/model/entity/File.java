package com.bftcom.mediastorage.model.entity;

public class File extends BaseEntity {

    private String name;

    private byte[] data;

    public File(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public File(Long id, String name, byte[] data) {
        super(id);
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
