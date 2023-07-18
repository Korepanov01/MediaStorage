package com.bftcom.mediastorage.data.model;

public class MediaTag extends BaseModel {

    private long mediaId;

    private long tagId;

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }
}
