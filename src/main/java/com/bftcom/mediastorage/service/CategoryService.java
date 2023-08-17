package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.searchparameters.CategorySearchParameters;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends ParameterSearchService<Category, CategorySearchParameters> {

    private final CategoryRepository categoryRepository;

    @Override
    protected ParametersSearchRepository<Category, CategorySearchParameters> getMainRepository() {
        return categoryRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Category entity) {
        return categoryRepository.existsByName(entity.getName());
    }
}
