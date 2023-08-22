package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.TooManyFilesException;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.FileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FileService extends CrudService<File> {

    private final FileRepository fileRepository;
    private final MediaFileRepository mediaFileRepository;

    static public String getFileUrl(@NonNull Long fileId) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/files/{id}")
                .buildAndExpand(fileId)
                .toUriString();
    }

    @Transactional
    public void save(@NonNull File file, @NonNull Long mediaId, @NonNull Long fileTypeId)
            throws TooManyFilesException {
        if (mediaFileRepository.countByMediaId(mediaId) > 5)
            throw new TooManyFilesException("Слишком много файлов");

        fileRepository.save(file);
        mediaFileRepository.save(new MediaFile(mediaId, file.getId(), fileTypeId));
    }

    @Override
    protected CrudRepository<File> getMainRepository() {
        return fileRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull File file) {
        return false;
    }
}
