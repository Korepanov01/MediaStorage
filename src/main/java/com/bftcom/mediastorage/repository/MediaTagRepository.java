package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaTag;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface MediaTagRepository extends CrudRepository<MediaTag> {

    @Transactional(readOnly = true)
    Integer mediaTagsCount(@NotNull Long mediaId);

    @Transactional(readOnly = true)
    Optional<MediaTag> findByMediaIdTagId(@NotNull Long mediaId, @NotNull Long tagId);

    @Transactional(readOnly = true)
    boolean existsByMediaIdTagId(@NotNull Long mediaId, @NotNull Long tagId);

    @Transactional(readOnly = true)
    boolean isExists(@NotNull Long mediaId, @NotNull Long tagId);
}
