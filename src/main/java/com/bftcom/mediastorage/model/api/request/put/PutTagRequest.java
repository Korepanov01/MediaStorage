package com.bftcom.mediastorage.model.api.request.put;

import com.bftcom.mediastorage.model.api.request.Request;
import com.bftcom.mediastorage.model.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PutTagRequest extends Request {

    @NotBlank(message = "Имя тега не может быть пустым")
    @Size(max = 200, message = "Имя тега не может быть длиннее 200 символов")
    private String name;

    static public Tag convertToTag(PutTagRequest request) {
        return new Tag(request.getName());
    }
}
