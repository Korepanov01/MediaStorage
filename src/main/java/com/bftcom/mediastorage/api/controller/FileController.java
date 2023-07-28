package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "name" + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/.png")
                .body(file.getData());

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestPart
            MultipartFile multipartFile
//            @RequestPart
//            @Valid
//            UploadFileRequest request
        ) {

        if (multipartFile.getSize() > 5 * 1024 * 1024) {
            return Response.getFileTooBig("Файл не может быть больше 5МБ");
        }

        File file;
        try {
            file = new File(multipartFile.getName(), multipartFile.getBytes());
        }
        catch (IOException exception) {
            return Response.getBadFileReading();
        }

        try {
            fileService.save(file);
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(file.getId()));
    }
}
