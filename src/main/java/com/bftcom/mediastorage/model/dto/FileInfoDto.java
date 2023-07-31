package com.bftcom.mediastorage.model.dto;


import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileInfoDto extends BaseDto {

    private final Long fileId;

    private final FileTypeDto fileType;

    public FileInfoDto(MediaFile mediaFile, FileType fileType) {
        this(mediaFile.getFileId(), new FileTypeDto(fileType));
    }
}
