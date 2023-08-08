package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostPutCategoryRequest extends PostPutEntityRequest<Category> {

    @NotBlank(message = "Имя категории не может быть пустым")
    @Size(max = 200, message = "Имя категории не может быть длиннее 200 символов")
    private String name;
    private Long parentCategoryId;

    @Override
    public Category covertToEntity() {
        return new Category(name, parentCategoryId);
    }
}
