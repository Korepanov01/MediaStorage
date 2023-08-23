package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.TooManyFilesException;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.FileRepository;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import com.bftcom.mediastorage.repository.MediaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService extends CrudService<File> {

    private final FileRepository fileRepository;
    private final MediaRepository mediaRepository;
    private final FileTypeRepository fileTypeRepository;

    static public String getFileUrl(@NonNull Long fileId) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/files/{id}")
                .buildAndExpand(fileId)
                .toUriString();
    }

    @Transactional
    public File save(@NonNull MultipartFile multipartFile, @NonNull Long mediaId, @NonNull Long fileTypeId)
            throws TooManyFilesException, EntityNotFoundException, IOException {
        Media media = mediaRepository.findById(mediaId);

        if (media == null)
            throw new EntityNotFoundException("Медиа не найдено");

        if (media.getFiles().size() > 5)
            throw new TooManyFilesException("Слишком много файлов");

        FileType fileType = fileTypeRepository.findById(fileTypeId);
        if (fileType == null)
            throw new EntityNotFoundException("Тип файла не найден");

        File file = new File(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getSize(),
                multipartFile.getBytes(),
                fileType);;

        media.addFile(file);

        mediaRepository.update(media);

        return file;
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
