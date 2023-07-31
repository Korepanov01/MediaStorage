package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagRepositoryTests {

    private final TagRepository repository;

    @Autowired
    public TagRepositoryTests(TagRepository repository) {
        this.repository = repository;
    }

    @Test
    public void FoundById() {
        Assertions.assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    public void FoundByMediaId() {
        TagSearchParameters parameters = new TagSearchParameters();
        parameters.setMediaId(1L);

        Assertions.assertFalse(repository.findByParameters(parameters).isEmpty());
    }
}
