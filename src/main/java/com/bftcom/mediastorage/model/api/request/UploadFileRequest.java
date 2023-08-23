package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class UploadFileRequest {

    @NotNull(message = "Должен быть указан тип файла")
    private Long fileTypeId;
}
