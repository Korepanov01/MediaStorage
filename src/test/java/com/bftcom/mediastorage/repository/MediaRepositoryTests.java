package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql("/test-schema.sql"), @Sql("/test-data.sql")})
public class MediaRepositoryTests {

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void testFindById() {
        Long mediaId = 1L;

        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);

        Assert.assertTrue(mediaOptional.isPresent());
        Media media = mediaOptional.get();
        Assert.assertEquals(mediaId, media.getId());
    }

    @Test
    public void testFindByTags() {
        List<Long> tagsIds = List.of(1L, 2L);
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setTagIds(tagsIds);

        List<Media> mediaList = mediaRepository.findByParameters(parameters);

        Assert.assertFalse(mediaList.isEmpty());
        for (Media media : mediaList) {
            boolean any = false;
            for (Long tagId : tagsIds) {
                if (media.getTags().stream().anyMatch(tag -> tag.getId().equals(tagId))) {
                    any = true;
                    break;
                }
            }

            Assert.assertTrue(any);
        }
    }

    @Test
    public void testFindByCategory() {
        Long categoryId = 1L;
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setCategoryId(categoryId);

        List<Media> mediaList = mediaRepository.findByParameters(parameters);

        Assert.assertFalse(mediaList.isEmpty());
    }

    @Test
    public void testFindBySearchString() {
        String searchString = "3";
        MediaSearchParameters parameters = new MediaSearchParameters();
        parameters.setSearchString(searchString);

        List<Media> mediaList = mediaRepository.findByParameters(parameters);

        Assert.assertFalse(mediaList.isEmpty());
        for (Media media : mediaList) {
            Assert.assertTrue(media.getName().toLowerCase().contains(searchString.toLowerCase()));
        }
    }

    @Test
    public void testFindByAllParameters() {
        String searchString = "3";
        List<Long> tagsIds = List.of(3L);
        Long categoryId = 2L;

        MediaSearchParameters parameters = new MediaSearchParameters();

        parameters.setSearchString(searchString);
        parameters.setCategoryId(categoryId);
        parameters.setTagIds(tagsIds);

        List<Media> mediaList = mediaRepository.findByParameters(parameters);

        Assert.assertFalse(mediaList.isEmpty());
    }

    @Test
    @Transactional
    public void testDeleteMedia() {
        Long mediaIdToDelete = 10L;
        Media existingMedia = mediaRepository.findById(mediaIdToDelete).orElse(null);
        Assert.assertNotNull(existingMedia);

        mediaRepository.delete(existingMedia);

        Optional<Media> deletedMediaOptional = mediaRepository.findById(mediaIdToDelete);
        Assert.assertFalse(deletedMediaOptional.isPresent());
    }
}
