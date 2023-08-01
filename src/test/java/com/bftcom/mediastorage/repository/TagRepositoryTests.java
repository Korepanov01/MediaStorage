package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagRepositoryTests {

    @Autowired
    private TagRepository repository;

    @Test
    public void FoundById() {
        Assertions.assertFalse(repository.findById(1L).isPresent());
    }

    @Test
    public void FoundByMediaId() {
        TagSearchParameters parameters = new TagSearchParameters();
        parameters.setMediaId(1L);

        Assertions.assertTrue(repository.findByParameters(parameters).isEmpty());
    }
}
