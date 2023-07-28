package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;
import com.bftcom.mediastorage.repository.CategoryRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends CrudService<Category, CategorySearchParameters> {

    private final CategoryRepository categoryRepository;

    @Override
    protected ParametersSearchRepository<Category, CategorySearchParameters> getMainRepository() {
        return categoryRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Category entity) {
        return categoryRepository.findByName(entity.getName()).isPresent();
    }
}
