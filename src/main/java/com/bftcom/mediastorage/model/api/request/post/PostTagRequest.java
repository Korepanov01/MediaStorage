package com.bftcom.mediastorage.model.api.request.post;

import com.bftcom.mediastorage.model.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostTagRequest extends PostEntityRequest<Tag> {

    @NotBlank(message = "Имя тега не может быть пустым")
    @Size(max = 200, message = "Имя тега не может быть длиннее 200 символов")
    private String name;

    @Override
    public Tag covertToEntity() {
        return new Tag(name);
    }
}
