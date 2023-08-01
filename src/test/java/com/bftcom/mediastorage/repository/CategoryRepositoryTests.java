package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.CategorySearchParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void FoundByParameters() {
        CategorySearchParameters parameters = new CategorySearchParameters();
        parameters.setParentCategoryId(0L);

        Assertions.assertTrue(repository.findByParameters(parameters).isEmpty());
    }
}
