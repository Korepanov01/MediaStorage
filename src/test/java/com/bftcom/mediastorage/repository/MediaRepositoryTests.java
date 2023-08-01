package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MediaRepositoryTests {

    @Autowired
    private MediaRepository repository;

    @Test
    public void FoundByTags() {
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setTagIds(List.of(1L, 2L, 3L, 4L));

        Assert.assertTrue(repository.findByParameters(parameters).isEmpty());
    }

    @Test
    public void FoundByAndCategory() {
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setTagIds(List.of(1L, 2L, 3L, 4L));
        parameters.setCategoryId(3L);

        Assertions.assertTrue(repository.findByParameters(parameters).isEmpty());
    }
}
