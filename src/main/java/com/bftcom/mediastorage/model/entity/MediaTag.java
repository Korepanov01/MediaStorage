package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaTag extends BaseEntity {
    private Long mediaId;
    private Long tagId;

    public MediaTag(Long id, Long mediaId, Long tagId) {
        super(id);
        this.mediaId = mediaId;
        this.tagId = tagId;
    }
}
