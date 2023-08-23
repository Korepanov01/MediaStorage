package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class FileTypeDto {
    private final Long id;
    private final String name;

    public FileTypeDto(@NonNull FileType fileType) {
        this(fileType.getId(), fileType.getName());
    }
}
