package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.MediaSearchParameters;

import java.util.List;

public interface MediaRepository extends CrudRepository<Media> {

    List<Media> findByTag(Tag tag);

    List<Media> findByCategory(Category category);

    List<Media> findByName(String name);

    List<Media> findRandom(int maxSize);

    List<Media> findByParameters(MediaSearchParameters parameters);
}
