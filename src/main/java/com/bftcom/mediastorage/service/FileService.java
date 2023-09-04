package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.exception.InvalidFileTypeException;
import com.bftcom.mediastorage.exception.TooManyFilesException;
import com.bftcom.mediastorage.data.entity.File;
import com.bftcom.mediastorage.data.entity.FileType;
import com.bftcom.mediastorage.data.entity.Media;
import com.bftcom.mediastorage.data.entity.MediaType;
import com.bftcom.mediastorage.data.repository.CustomJpaRepository;
import com.bftcom.mediastorage.data.repository.FileRepository;
import com.bftcom.mediastorage.data.repository.FileTypeRepository;
import com.bftcom.mediastorage.data.repository.MediaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public File save(@NonNull MultipartFile multipartFile, @NonNull Long mediaId, @NonNull Long fileTypeId)
            throws TooManyFilesException, EntityNotFoundException, IOException, InvalidFileTypeException, IllegalOperationException {
        Media media = mediaRepository.findById(mediaId).orElseThrow(
                () -> new EntityNotFoundException("Медиа не найдено"));

        if (media.getFiles().size() > 5)
            throw new TooManyFilesException("Слишком много файлов");

        FileType fileType = fileTypeRepository.findById(fileTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Тип файла не найден"));

        File file = new File(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getSize(),
                multipartFile.getBytes(),
                fileType);

        validateFileType(file, media);

        fileRepository.save(file);

        media.addFile(file);

        mediaRepository.save(media);

        return file;
    }

    private void validateFileType(File file, Media media) throws InvalidFileTypeException, IllegalOperationException {
        String contentType = file.getContentType();
        String fileTypeName = file.getFileType().getName();
        String mediaTypeName = media.getMediaType().getName();
        if (fileTypeName.equals(FileType.THUMBNAIL) && media.getFiles().stream().anyMatch(mediaFile -> mediaFile.getFileType().getName().equals(FileType.THUMBNAIL)))
            throw new IllegalOperationException("Не может быть больше одного первью");

        if (contentType.startsWith("image")) {
            if (!fileTypeName.equals(FileType.THUMBNAIL) && !mediaTypeName.equals(MediaType.IMAGE))
                throw new InvalidFileTypeException("Изображение может быть только в медиа типа '" + MediaType.IMAGE + "'");
        } else {
            if (fileTypeName.equals(FileType.THUMBNAIL)) {
                throw new InvalidFileTypeException("Превью может быть только изображением");
            }
            if (contentType.startsWith("audio")) {
                if (!mediaTypeName.equals(MediaType.AUDIO))
                    throw new InvalidFileTypeException("Аудио может быть только в медиа типа '" + MediaType.AUDIO + "'");
            } else if (contentType.startsWith("video")) {
                if (!mediaTypeName.equals(MediaType.VIDEO))
                    throw new InvalidFileTypeException("Видео может быть только в медиа типа '" + MediaType.VIDEO + "'");
            } else {
                throw new InvalidFileTypeException("Недопустимый тип файла");
            }
        }
    }

    @Override
    protected CustomJpaRepository<File> getMainRepository() {
        return fileRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull File file) {
        return false;
    }
}
