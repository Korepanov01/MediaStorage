package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.searchparameters.CategorySearchParameters;

public interface CategoryRepository extends ParametersSearchRepository<Category, CategorySearchParameters>,
        NameSearchRepository<Category> {

}
