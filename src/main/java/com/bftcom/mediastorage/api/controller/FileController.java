package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

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
            @RequestPart(name = "file")
            MultipartFile multipartFile,
            @RequestPart(name = "mediaId")
            String stringMediaId,
            @RequestPart(name = "fileTypeId")
            String stringFileTypeId
    ) {
        if (multipartFile.getSize() > 5 * 1024 * 1024) {
            return Response.getFileTooBig("Файл не может быть больше 5МБ");
        }

        long mediaId;
        try {
            mediaId = Long.parseLong(stringMediaId);
        }
        catch (NumberFormatException exception) {
            return Response.getResponse("mediaId не является числом", HttpStatus.BAD_REQUEST);
        }

        long fileTypeId;
        try {
            fileTypeId = Long.parseLong(stringFileTypeId);
        }
        catch (NumberFormatException exception) {
            return Response.getResponse("fileTypeId не является числом", HttpStatus.BAD_REQUEST);
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
}
