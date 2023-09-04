package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.web.controller.interfaces.GetAllController;
import com.bftcom.mediastorage.web.controller.interfaces.GetByIdController;
import com.bftcom.mediastorage.web.model.dto.FileTypeDto;
import com.bftcom.mediastorage.data.entity.FileType;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.FileTypeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file_types")
@RequiredArgsConstructor
public class FileTypeController implements GetByIdController<FileTypeDto, FileType>, GetAllController<FileTypeDto, FileType> {

    private final FileTypeService fileTypeService;

    @Override
    public FileTypeDto convertToDto(@NonNull FileType fileType) {
        return new FileTypeDto(fileType);
    }

    @Override
    public FileTypeDto convertToListItemDto(@NonNull FileType fileType) {
        return new FileTypeDto(fileType);
    }

    @Override
    public CrudService<FileType> getMainService() {
        return fileTypeService;
    }
}
