package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class UploadFileRequest {

    @NotNull(message = "Должен быть указан id типа файла (fileTypeId)")
    private Long fileTypeId;

    @NotNull(message = "Должен быть указан id медиа (mediaId)")
    private Long mediaId;
}
