package com.bftcom.mediastorage.data.model;

public class File extends BaseModel {

    private String path;

    private Short size;

    private String extension;

    public File(Long id, String path, Short size, String extension) {
        super(id);
        this.path = path;
        this.size = size;
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
