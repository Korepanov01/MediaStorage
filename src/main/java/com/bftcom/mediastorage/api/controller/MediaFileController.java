package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.model.dto.FileInfoDto;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.searchparameters.MediaFilesSearchParameters;
import com.bftcom.mediastorage.service.FileTypeService;
import com.bftcom.mediastorage.service.MediaFileService;
import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media_file")
@RequiredArgsConstructor
public class MediaFileController implements ParametersSearchController<FileInfoDto, MediaFile, MediaFilesSearchParameters> {

    private final FileTypeService fileTypeService;
    private final MediaFileService mediaFileService;

    @Override
    public FileInfoDto convertToDto(MediaFile mediaFile) {
        FileType fileType = fileTypeService.findById(mediaFile.getFileTypeId()).orElseThrow();
        return new FileInfoDto(mediaFile, fileType);
    }

    @Override
    public ParameterSearchService<MediaFile, MediaFilesSearchParameters> getMainService() {
        return mediaFileService;
    }
}
