package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql("/test-schema.sql"), @Sql("/test-data.sql")})
public class TagRepositoryTests {

    @Autowired
    private TagRepository repository;

    @Test
    public void FoundById() {
        Assert.assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    public void FoundByMediaId() {
        TagSearchParameters parameters = new TagSearchParameters();
        parameters.setMediaId(1L);

        Assert.assertFalse(repository.findByParameters(parameters).isEmpty());
    }
}
