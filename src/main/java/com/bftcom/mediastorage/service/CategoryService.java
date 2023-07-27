package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategorySearchParameters> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected ParametersSearchRepository<Category, CategorySearchParameters> getMainRepository() {
        return categoryRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Category entity) {
        return categoryRepository.findByName(entity.getName()).isPresent();
    }
}
