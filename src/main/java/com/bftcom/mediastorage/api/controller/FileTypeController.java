package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.FileTypeDto;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.post.PostFileTypeRequest;
import com.bftcom.mediastorage.service.FileTypeService;
import com.bftcom.mediastorage.service.ParameterSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file_types")
@RequiredArgsConstructor
public class FileTypeController implements FullController<
        FileTypeDto,
        FileType,
        PostFileTypeRequest,
        SearchStringParameters> {

    private final FileTypeService fileTypeService;

    @Override
    public FileTypeDto convertToDto(FileType fileType) {
        return new FileTypeDto(fileType);
    }

    @Override
    public ParameterSearchService<FileType, SearchStringParameters> getMainService() {
        return fileTypeService;
    }
}
