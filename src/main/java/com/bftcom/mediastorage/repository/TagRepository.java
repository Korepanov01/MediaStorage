package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import lombok.NonNull;

import java.util.List;

public interface TagRepository extends ParametersSearchRepository<Tag, TagSearchParameters>,
        NameSearchRepository<Tag> {
    List<Tag> getByMediaId(@NonNull Long mediaId);
}
