package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.DeleteController;
import com.bftcom.mediastorage.exception.TooManyFilesException;
import com.bftcom.mediastorage.model.api.request.UploadFileRequest;
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

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FileController implements DeleteController<File> {

    private final FileService fileService;

    @GetMapping("/api/files/{id}")
    public ResponseEntity<?> downloadFile(
            @PathVariable
            Long id) {
        File file = fileService.findById(id);

        if (file == null) {
            return Response.getEntityNotFound("Файл не найден");
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
        } catch (TooManyFilesException | IOException exception) {
            return Response.getBadRequest(exception.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(file.getId()));
    }

    @DeleteMapping(path = "/api/media/{id}/delete_file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> deleteFile(
            @PathVariable
            Long id,
            Long fileId) {

        fileService.delete(fileId);

        return Response.getOk();
    }

    @Override
    public CrudService<File> getMainService() {
        return fileService;
    }
}
