package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaFile extends BaseEntity {

    @NotNull
    private Long mediaId;

    @NotNull
    private Long fileId;

    @NotNull
    private Long fileTypeId;

    public MediaFile(Long id, Long mediaId, Long fileId, Long fileTypeId) {
        super(id);
        this.mediaId = mediaId;
        this.fileId = fileId;
        this.fileTypeId = fileTypeId;
    }
}
