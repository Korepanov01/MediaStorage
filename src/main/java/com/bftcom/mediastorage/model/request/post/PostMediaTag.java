package com.bftcom.mediastorage.model.request.post;

import com.bftcom.mediastorage.model.entity.MediaTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMediaTag extends PostEntityRequest<MediaTag> {

    @NotNull(message = "Должен быть указан id медиа (mediaId)")
    private Long mediaId;

    @NotNull(message = "Должен быть указан id тега (tagId)")
    private Long tagId;

    @Override
    public MediaTag covertToEntity() {
        return new MediaTag(mediaId, tagId);
    }
}
