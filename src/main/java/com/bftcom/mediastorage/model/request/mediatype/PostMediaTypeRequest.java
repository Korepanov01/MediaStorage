package com.bftcom.mediastorage.model.request.mediatype;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.model.request.PostEntityRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMediaTypeRequest extends PostEntityRequest<MediaType> {
    @NotBlank(message = "Имя категории не может быть пустым")
    @Size(max = 100, message = "Имя категории не может быть длиннее 200 символов")
    private String name;

    @Override
    public MediaType covertToEntity() {
        return new MediaType(name);
    }
}
