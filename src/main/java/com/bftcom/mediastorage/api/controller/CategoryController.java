package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.CrudController;
import com.bftcom.mediastorage.model.api.request.PutCategoryRequest;
import com.bftcom.mediastorage.model.dto.CategoryDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.api.request.PostCategoryRequest;
import com.bftcom.mediastorage.service.CategoryService;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController implements CrudController<
        CategoryDto,
        Category,
        PostCategoryRequest,
        PutCategoryRequest> {

    private final CategoryService categoryService;

    @Override
    public CategoryDto convertToDto(@NonNull Category category) {
        return new CategoryDto(category);
    }

    @Override
    public CrudService<Category> getMainService() {
        return categoryService;
    }

    @Override
    public Category fillEntity(@NonNull PostCategoryRequest request) throws Exception {
        Category parentCategory = null;

        if (request.getParentCategoryId() != null && request.getParentCategoryId() != 0)
            parentCategory = categoryService.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Не найдена родительская категория"));

        return new Category(request.getName(), parentCategory);
    }

    @Override
    public void updateEntity(@NonNull Category category, @NonNull PutCategoryRequest request) {
        category.setName(request.getName());
    }
}
