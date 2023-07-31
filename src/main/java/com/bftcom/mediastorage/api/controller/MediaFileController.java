package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.FileInfoDto;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.parameters.MediaFilesSearchParameters;
import com.bftcom.mediastorage.service.FileTypeService;
import com.bftcom.mediastorage.service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media_file")
@RequiredArgsConstructor
public class MediaFileController {

    private final FileTypeService fileTypeService;
    private final MediaFileService mediaFileService;

    @GetMapping
    public List<FileInfoDto> get(
            MediaFilesSearchParameters parameters) {
        return mediaFileService
                .findByParameters(parameters)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FileInfoDto convertToDto(MediaFile mediaFile) {
        FileType fileType = fileTypeService.findById(mediaFile.getFileTypeId()).orElseThrow();
        return new FileInfoDto(mediaFile, fileType);
    }
}
