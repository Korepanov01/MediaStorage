package com.bftcom.mediastorage.model.request.tag;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PutTagRequest extends Request {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 200, message = "Name length must be no more than 200")
    private String name;

    static public Tag convertToTag(PutTagRequest request) {
        return new Tag(request.getName());
    }
}
