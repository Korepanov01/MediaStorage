package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class JdbcTagRepositoryTests {

    private final JdbcTagRepository repository;

    @Autowired
    public JdbcTagRepositoryTests(JdbcTagRepository repository) {
        this.repository = repository;
    }

    @Test
    public void WhenSaved_ThenFoundById() {
        Tag tag = new Tag(UUID.randomUUID().toString());

        tag = repository.save(new Tag(UUID.randomUUID().toString()));

        Assertions.assertTrue(repository.findById(tag.getId()).isPresent());
    }
}
