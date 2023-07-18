package com.bftcom.mediastorage.data.model;

public class MediaFile extends BaseModel {

    private long mediaId;

    private long fileId;

    private long fileTypeId;

    public MediaFile(Long id, long mediaId, long fileId, long fileTypeId) {
        super(id);
        this.mediaId = mediaId;
        this.fileId = fileId;
        this.fileTypeId = fileTypeId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(long fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }
}
