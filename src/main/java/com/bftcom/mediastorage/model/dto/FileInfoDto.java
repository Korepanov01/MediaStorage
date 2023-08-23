package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.service.FileService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfoDto {
    private final Long id;
    private final String url;
    private final String type;

    public FileInfoDto(File file) {
        this(
                file.getId(),
                FileService.getFileUrl(file.getId()),
                file.getFileType().getName()
        );
    }
}
