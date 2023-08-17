package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.GetByIdController;
import com.bftcom.mediastorage.model.dto.FileTypeDto;
import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.FileTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file_types")
@RequiredArgsConstructor
public class FileTypeController implements GetByIdController<FileTypeDto, FileType> {

    private final FileTypeService fileTypeService;

    @Override
    public FileTypeDto convertToDto(FileType fileType) {
        return new FileTypeDto(fileType);
    }

    @Override
    public CrudService<FileType> getMainService() {
        return fileTypeService;
    }
}
