package com.bftcom.mediastorage.repository;

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
        Assert.assertNotNull(repository.findById(1L));
    }
}
