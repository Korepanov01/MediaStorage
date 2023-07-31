package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.CategorySearchParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTests {

    private final CategoryRepository repository;

    @Autowired
    public CategoryRepositoryTests(CategoryRepository repository) {
        this.repository = repository;
    }

    @Test
    public void FoundByParameters() {
        CategorySearchParameters parameters = new CategorySearchParameters();
        parameters.setParentCategoryId(0L);

        Assertions.assertFalse(repository.findByParameters(parameters).isEmpty());
    }
}
