package com.bftcom.mediastorage.data.model;

public class MediaTag extends BaseModel {

    private Long mediaId;

    private Long tagId;

    public MediaTag(Long id, Long mediaId, Long tagId) {
        super(id);
        this.mediaId = mediaId;
        this.tagId = tagId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
