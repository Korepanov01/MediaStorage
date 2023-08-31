package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.TooManyTagsException;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.repository.MediaRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql("/test-schema.sql"), @Sql("/test-data.sql")})
public class MediaServiceTests {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MediaService mediaService;

    @Test
    public void addTag_ValidTag_Success() {
        Media media = mediaRepository.findById(1L).orElseThrow();
        Tag tag = tagRepository.findById(1L).orElseThrow();

        assertDoesNotThrow(() -> mediaService.addTag(media.getId(), tag.getId()));

        Media updatedMedia = mediaRepository.findById(media.getId()).orElse(null);
        assertNotNull(updatedMedia);
        assertTrue(updatedMedia.getTags().contains(tag));
    }

    @Test
    public void addTag_TooManyTags_ExceptionThrown() {
        Media media = mediaRepository.findById(1L).orElseThrow();

        for (int i = 0; i < 21; i++) {
            Tag tag = new Tag(UUID.randomUUID().toString());
            tagRepository.save(tag);
            try {
                mediaService.addTag(media.getId(), tag.getId());
            } catch (Exception ignored) {

            }
        }

        Tag extraTag = new Tag(UUID.randomUUID().toString());
        tagRepository.save(extraTag);

        assertThrows(TooManyTagsException.class, () -> mediaService.addTag(media.getId(), extraTag.getId()));
    }

    @Test
    public void removeTag_ValidTag_Success() {
        // Arrange
        Media media = mediaRepository.findById(1L).orElseThrow();

        Tag tag = new Tag(UUID.randomUUID().toString());
        tagRepository.save(tag);

        try {
            mediaService.addTag(media.getId(), tag.getId());
        } catch (Exception ignored) {
        }

        assertDoesNotThrow(() -> mediaService.removeTag(media.getId(), tag.getId()));

        Media updatedMedia = mediaRepository.findById(media.getId()).orElse(null);
        assertNotNull(updatedMedia);
        assertFalse(updatedMedia.getTags().contains(tag));
    }
}
