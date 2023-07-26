package com.bftcom.mediastorage.model.request.tag;

import com.bftcom.mediastorage.model.entity.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostTagRequest {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 200, message = "Name length must be no more than 200")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public Tag convertToTag(PostTagRequest request) {
        return new Tag(request.getName());
    }
}
