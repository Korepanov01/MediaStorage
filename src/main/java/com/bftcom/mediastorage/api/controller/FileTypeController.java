package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.FileTypeDto;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.filetype.PostFileTypeRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.FileTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file_types")
@RequiredArgsConstructor
public class FileTypeController extends CrudController<
        FileTypeDto,
        FileType,
        PostFileTypeRequest,
        SearchStringParameters> {

    private final FileTypeService fileTypeService;

    @Override
    protected FileTypeDto convertToDto(FileType fileType) {
        return new FileTypeDto(fileType);
    }

    @Override
    protected ParameterSearchService<FileType, SearchStringParameters> getMainService() {
        return fileTypeService;
    }
}
