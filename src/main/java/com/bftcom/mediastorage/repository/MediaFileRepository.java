package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.searchparameters.MediaFilesSearchParameters;
import lombok.NonNull;

import java.util.List;

public interface MediaFileRepository extends ParametersSearchRepository<MediaFile, MediaFilesSearchParameters> {

    List<MediaFile> findByMediaIdAndFileType(@NonNull Long mediaId, @NonNull String fileType);

    List<MediaFile> findByMediaId(@NonNull Long mediaId);
}
