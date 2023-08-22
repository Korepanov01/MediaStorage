package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class PostMediaTag implements ToEntityConvertable<MediaTag> {

    @NotNull(message = "Должен быть указан id медиа (mediaId)")
    private Long mediaId;

    @NotNull(message = "Должен быть указан id тега (tagId)")
    private Long tagId;

    @Override
    public MediaTag covertToEntity() {
        return new MediaTag(mediaId, tagId);
    }
}
