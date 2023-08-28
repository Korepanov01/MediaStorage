package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends CrudService<Category> {

    private final CategoryRepository categoryRepository;

    public List<Category> findByParentCategoryId(@NonNull Long parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId);
    }

    public List<Long> findAllSubcategoryIds(@NonNull Long categoryId) throws EntityNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        List<Long> subcategoryIds = new ArrayList<>();
        findAllSubcategoryIdsRecursive(category, subcategoryIds);
        return subcategoryIds;
    }

    private void findAllSubcategoryIdsRecursive(Category category, List<Long> subcategoryIds) {
        subcategoryIds.add(category.getId());
        for (Category childCategory : category.getChildrenCategories()) {
            findAllSubcategoryIdsRecursive(childCategory, subcategoryIds);
        }
    }

    @Override
    protected CustomJpaRepository<Category> getMainRepository() {
        return categoryRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Category category) {
        return categoryRepository.existsByName(category.getName());
    }
}
