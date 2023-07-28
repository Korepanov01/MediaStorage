package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaTag extends BaseEntity {
    @NotNull
    private Long mediaId;
    @NotNull
    private Long tagId;

    public MediaTag(Long id, Long mediaId, Long tagId) {
        super(id);
        this.mediaId = mediaId;
        this.tagId = tagId;
    }
}
