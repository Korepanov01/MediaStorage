package com.bftcom.mediastorage.model.request.tag;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.request.PostEntityRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostTagRequest extends PostEntityRequest<Tag> {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 200, message = "Name length must be no more than 200")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Tag covertToEntity() {
        return new Tag(name);
    }
}
