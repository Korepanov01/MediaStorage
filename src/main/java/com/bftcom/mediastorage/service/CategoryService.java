package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends CrudService<Category> {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> findByParentCategoryId(@NonNull Long parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId);
    }

    @Override
    protected CrudRepository<Category> getMainRepository() {
        return categoryRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Category category) {
        return categoryRepository.existsByName(category.getName());
    }
}
