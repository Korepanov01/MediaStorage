package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MediaRepositoryTests {

    private final MediaRepository repository;

    @Autowired
    public MediaRepositoryTests(MediaRepository repository) {
        this.repository = repository;
    }

    @Test
    public void FoundByTags() {
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setTagIds(List.of(1L, 2L, 3L, 4L));

        Assertions.assertFalse(repository.findByParameters(parameters).isEmpty());
    }

    @Test
    public void FoundByAndCategory() {
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setTagIds(List.of(1L, 2L, 3L, 4L));
        parameters.setCategoryId(3L);

        Assertions.assertFalse(repository.findByParameters(parameters).isEmpty());
    }
}
