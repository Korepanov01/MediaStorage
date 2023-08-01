package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.FileRepository;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService extends CrudService<File> {

    private final FileRepository fileRepository;
    private final MediaFileRepository mediaFileRepository;

    @Transactional
    public void save(@NonNull File file, @NonNull Long mediaId, @NonNull Long fileTypeId)
            throws EntityAlreadyExistsException {

        fileRepository.save(file);

        MediaFile mediaFile = new MediaFile(mediaId, file.getId(), fileTypeId);
        mediaFileRepository.save(mediaFile);
    }

    @Override
    protected CrudRepository<File> getMainRepository() {
        return fileRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull File file) {
        return false;
    }
}
