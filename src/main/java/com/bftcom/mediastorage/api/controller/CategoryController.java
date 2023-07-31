package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.CategoryDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;
import com.bftcom.mediastorage.model.request.category.PostCategoryRequest;
import com.bftcom.mediastorage.service.CategoryService;
import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController implements FullController<
        CategoryDto,
        Category,
        PostCategoryRequest,
        CategorySearchParameters> {

    private final CategoryService categoryService;

    @Override
    public CategoryDto convertToDto(Category category) {
        return new CategoryDto(category);
    }

    @Override
    public ParameterSearchService<Category, CategorySearchParameters> getMainService() {
        return categoryService;
    }
}
