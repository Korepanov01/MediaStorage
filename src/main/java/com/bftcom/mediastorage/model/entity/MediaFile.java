package com.bftcom.mediastorage.model.entity;

public class MediaFile extends BaseEntity {

    private Long mediaId;

    private Long fileId;

    private Long fileTypeId;

    public MediaFile(Long id, Long mediaId, Long fileId, Long fileTypeId) {
        super(id);
        this.mediaId = mediaId;
        this.fileId = fileId;
        this.fileTypeId = fileTypeId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Long fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
}
