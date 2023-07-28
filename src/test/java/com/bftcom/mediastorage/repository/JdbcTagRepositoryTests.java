package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.parameters.TagSearchParameters;
import com.bftcom.mediastorage.repository.jdbc.JdbcTagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcTagRepositoryTests {

    private final JdbcTagRepository repository;

    @Autowired
    public JdbcTagRepositoryTests(JdbcTagRepository repository) {
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
