package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Category;
import com.bftcom.mediastorage.data.entity.Media;
import com.bftcom.mediastorage.data.entity.Tag;

import java.util.List;

public interface MediaRepository extends CrudRepository<Media, Long> {

    List<Media> findByTag(Tag tag);

    List<Media> findByCategory(Category category);

    List<Media> findByName(String name);
}
