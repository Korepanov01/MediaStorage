package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.DeleteController;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.api.response.PostEntityResponse;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController implements DeleteController<File> {

    private final FileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadFile(
            @PathVariable
            Long id) {
        Optional<File> optionalFile = fileService.findById(id);

        if (optionalFile.isEmpty()) {
            return Response.getEntityNotFound("Файл не найден");
        }

        File file = optionalFile.get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(file.getData());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestParam(name = "file")
            MultipartFile multipartFile,
            long mediaId,
            long fileTypeId) {
        if (multipartFile.getSize() > 5 * 1024 * 1024) {
            return Response.getFileTooBig("Файл не может быть больше 5МБ");
        }

        File file;
        try {
            file = new File(
                    multipartFile.getName(),
                    multipartFile.getContentType(),
                    multipartFile.getSize(),
                    multipartFile.getBytes());
        }
        catch (IOException exception) {
            return Response.getBadFileReading();
        }

        try {
            fileService.save(file, mediaId, fileTypeId);
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(file.getId()));
    }

    @Override
    public CrudService<File> getMainService() {
        return fileService;
    }
}
