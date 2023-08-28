package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.CrudController;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.model.api.PostCategoryRequest;
import com.bftcom.mediastorage.model.api.PutCategoryRequest;
import com.bftcom.mediastorage.model.dto.CategoryDto;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.service.CategoryService;
import com.bftcom.mediastorage.service.CrudService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController implements CrudController<
        CategoryDto,
        Category,
        PostCategoryRequest,
        PutCategoryRequest> {

    private final CategoryService categoryService;

    @GetMapping("/{id}/children")
    public List<CategoryDto> getChildren(
            @PathVariable
            Long id) {
        return categoryService
                .findByParentCategoryId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto convertToDto(@NonNull Category category) {
        return new CategoryDto(category);
    }

    @Override
    public CrudService<Category> getMainService() {
        return categoryService;
    }

    @Override
    public Category fillEntity(@NonNull PostCategoryRequest request) throws EntityNotFoundException {
        Category parentCategory = null;

        if (request.getParentCategoryId() != null && request.getParentCategoryId() != 0) {
            parentCategory = categoryService.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Не найдена родительская категория"));
        }

        return new Category(request.getName(), parentCategory);
    }

    @Override
    public void updateEntity(@NonNull Category category, @NonNull PutCategoryRequest request) throws EntityExistsException {
        if (categoryService.existsByName(request.getName())) {
            throw new EntityExistsException("Такая запись уже существует");
        }

        category.setName(request.getName());
    }
}
