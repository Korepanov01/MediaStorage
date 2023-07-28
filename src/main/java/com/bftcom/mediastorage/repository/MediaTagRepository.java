package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaTag;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface MediaTagRepository extends CrudRepository<MediaTag> {

    Optional<MediaTag> findByMediaIdTagId(@NotNull Long mediaId, @NotNull Long tagId);

    boolean isExists(@NotNull Long mediaId, @NotNull Long tagId);
}
