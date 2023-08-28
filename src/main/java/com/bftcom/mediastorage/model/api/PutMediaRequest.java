package com.bftcom.mediastorage.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class PutMediaRequest {

    @NotNull(message = "Должена быть указана категория")
    private Long categoryId;

    @NotBlank(message = "Должно быть указано название")
    @Size(max = 200, message = "Название не должно быть больше 200 символов")
    private String name;

    @Size(max = 10_000, message = "Описание не должно быть больше 10.000 символов")
    private String description;

    private Long mediaTypeId;
}