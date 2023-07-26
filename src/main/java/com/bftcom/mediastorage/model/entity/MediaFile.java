package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
