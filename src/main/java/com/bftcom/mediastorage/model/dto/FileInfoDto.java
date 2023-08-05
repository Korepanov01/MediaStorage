package com.bftcom.mediastorage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileInfoDto extends BaseDto {

    private final String url;

    private final String fileType;
}
