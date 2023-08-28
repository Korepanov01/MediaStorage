package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Responses;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.exception.InvalidFileTypeException;
import com.bftcom.mediastorage.exception.TooManyFilesException;
import com.bftcom.mediastorage.model.api.request.UploadFileRequest;
import com.bftcom.mediastorage.model.api.response.PostEntityResponse;
import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/files/{id}")
    public ResponseEntity<?> downloadFile(
            @PathVariable
            Long id) {
        File file = fileService.findById(id).orElse(null);

        if (file == null) {
            return Responses.notFound("Файл не найден");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(file.getData());
    }

    @PostMapping(path = "/api/media/{id}/add_file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @PathVariable("id")
            Long mediaId,
            @RequestParam(name = "file")
            MultipartFile multipartFile,
            UploadFileRequest request) {
        File file;
        try {
            file = fileService.save(multipartFile, mediaId, request.getFileTypeId());
        } catch (TooManyFilesException | IOException | InvalidFileTypeException | IllegalOperationException e) {
            return Responses.badRequest(e.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(file.getId()));
    }

    @DeleteMapping(path = "/api/media/{id}/delete_file")
    public ResponseEntity<?> deleteFile(
            @PathVariable
            Long id,
            @RequestParam
            Long fileId) {

        try {
            fileService.delete(fileId);
        } catch (IllegalOperationException e) {
            return Responses.badRequest(e.getMessage());
        } catch (EntityNotFoundException e) {
            return Responses.notFound(e.getMessage());
        }

        return Responses.OK;
    }
}
