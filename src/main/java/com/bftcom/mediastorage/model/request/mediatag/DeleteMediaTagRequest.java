package com.bftcom.mediastorage.model.request.mediatag;

import com.bftcom.mediastorage.model.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteMediaTagRequest extends Request {

    @NotNull(message = "Должен быть указан id тега (tagId)")
    private Long tagId;

    @NotNull(message = "Должен быть указан id медиа (mediaId)")
    private Long mediaId;
}
