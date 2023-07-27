package com.bftcom.mediastorage.model.dto;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileTypeDto extends BaseDto {
    private final Long id;
    private final String name;

    public FileTypeDto(@NonNull FileType fileType) {
        this(fileType.getId(), fileType.getName());
    }
}
