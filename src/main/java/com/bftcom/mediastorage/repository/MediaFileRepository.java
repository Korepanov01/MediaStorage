package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaFile;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MediaFileRepository extends CrudRepository<MediaFile> {

    @Transactional(readOnly = true)
    List<MediaFile> findByMediaIdAndFileType(@NonNull Long mediaId, @NonNull String fileType);

    @Transactional(readOnly = true)
    List<MediaFile> findByMediaId(@NonNull Long mediaId);

    @Transactional(readOnly = true)
    Integer countByMediaId(@NonNull Long mediaId);
}
