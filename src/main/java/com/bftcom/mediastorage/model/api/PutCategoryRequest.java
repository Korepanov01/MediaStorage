package com.bftcom.mediastorage.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PutCategoryRequest {

    @NotBlank(message = "Имя категории не может быть пустым")
    @Size(max = 200, message = "Имя категории не может быть длиннее 200 символов")
    private String name;

}
