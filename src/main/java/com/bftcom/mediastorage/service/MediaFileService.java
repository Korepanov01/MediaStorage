package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaFileService extends CrudService<MediaFile> {

    private final MediaFileRepository repository;

    public List<MediaFile> getByMediaId(@NonNull Long mediaId) {
        return repository.findByMediaId(mediaId);
    }

    public String getThumbnailUrl(@NonNull Long mediaId) {
        List<MediaFile> mediaFiles = repository.findByMediaIdAndFileType(mediaId, FileType.THUMBNAIL);
        return mediaFiles.size() != 0 ? FileService.getFileUrl(mediaFiles.get(0).getFileId()) : null;
    }

    @Override
    protected CrudRepository<MediaFile> getMainRepository() {
        return repository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull MediaFile entity) {
        return false;
    }
}
