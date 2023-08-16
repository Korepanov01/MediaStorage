package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.searchparameters.MediaFilesSearchParameters;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaFileService extends ParameterSearchService<MediaFile, MediaFilesSearchParameters> {

    private final MediaFileRepository repository;

    public List<MediaFile> getByMediaId(@NonNull Long mediaId) {
        return repository.findByMediaId(mediaId);
    }

    public String getThumbnailUrl(@NonNull Long mediaId) {
        List<MediaFile> mediaFiles = repository.findByMediaIdAndFileType(mediaId, FileType.THUMBNAIL);
        return mediaFiles.size() != 0 ? FileService.getFileUrl(mediaFiles.get(0).getFileId()) : null;
    }

    public List<String> getMainUrls(@NonNull Long mediaId) {
        List<MediaFile> mediaFiles = repository.findByMediaIdAndFileType(mediaId, FileType.MAIN);
        return mediaFiles.stream().map(mediaFile -> FileService.getFileUrl(mediaFile.getFileId())).collect(Collectors.toList());
    }

    @Override
    protected ParametersSearchRepository<MediaFile, MediaFilesSearchParameters> getMainRepository() {
        return repository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull MediaFile entity) {
        return false;
    }
}
