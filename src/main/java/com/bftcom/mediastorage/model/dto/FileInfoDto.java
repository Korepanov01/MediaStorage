package com.bftcom.mediastorage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfoDto {
    private final Long id;
    private final String url;
    private final String type;
}
