package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.parameters.MediaSearchParameters;

import java.util.List;

public interface MediaRepository extends ParametersSearchRepository<Media, MediaSearchParameters> {

    List<Media> findRandom(int maxSize);
}
