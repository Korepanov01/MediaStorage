package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.CategoryDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;
import com.bftcom.mediastorage.model.request.category.PostCategoryRequest;
import com.bftcom.mediastorage.service.BaseService;
import com.bftcom.mediastorage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController extends BaseController<
        CategoryDto,
        Category,
        PostCategoryRequest,
        CategorySearchParameters> {

    private final CategoryService categoryService;

    @Override
    protected CategoryDto convertToDto(Category category) {
        return new CategoryDto(category);
    }

    @Override
    protected BaseService<Category, CategorySearchParameters> getMainService() {
        return categoryService;
    }
}
