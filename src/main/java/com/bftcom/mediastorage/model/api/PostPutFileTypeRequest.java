package com.bftcom.mediastorage.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PostPutFileTypeRequest {

    @NotBlank(message = "Имя типа не может быть пустым")
    @Size(max = 100, message = "Имя типа не может быть длиннее 100 символов")
    private String name;
}