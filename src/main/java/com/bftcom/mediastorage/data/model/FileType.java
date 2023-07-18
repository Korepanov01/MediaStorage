package com.bftcom.mediastorage.data.model;

public class FileType extends BaseModel {
    private String type;

    public FileType(Long id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
