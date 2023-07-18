package com.bftcom.mediastorage.data.model;

public class MediaTag extends BaseModel {

    private long mediaId;

    private long tagId;

    public MediaTag(Long id, long mediaId, long tagId) {
        super(id);
        this.mediaId = mediaId;
        this.tagId = tagId;
    }

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
