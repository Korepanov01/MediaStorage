package com.bftcom.mediastorage.model.request;

import com.bftcom.mediastorage.model.entity.Tag;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class PostTagRequest {

    @NotBlank(message = "Name must not be blank")
    //@Max(value = 200, message = "Name length must be no more than 200")
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
