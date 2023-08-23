package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PostPutTagRequest {

    @NotBlank(message = "Имя тега не может быть пустым")
    @Size(max = 200, message = "Имя тега не может быть длиннее 200 символов")
    private String name;
}
