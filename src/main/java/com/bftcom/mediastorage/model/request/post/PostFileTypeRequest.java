package com.bftcom.mediastorage.model.request.post;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostFileTypeRequest extends PostEntityRequest<FileType> {

    @NotBlank(message = "Имя типа не может быть пустым")
    @Size(max = 100, message = "Имя типа не может быть длиннее 100 символов")
    private String name;

    @Override
    public FileType covertToEntity() {
        return new FileType(name);
    }
}