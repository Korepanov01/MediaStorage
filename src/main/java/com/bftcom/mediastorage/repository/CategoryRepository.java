package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;

public interface CategoryRepository extends ParametersSearchRepository<Category, CategorySearchParameters>,
        NameSearchRepository<Category> {

}
