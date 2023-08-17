package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.repository.CrudRepository;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTypeService extends CrudService<FileType> {

    private final FileTypeRepository fileTypeRepository;

    @Override
    protected CrudRepository<FileType> getMainRepository() {
        return fileTypeRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull FileType fileType) {
        return fileTypeRepository.existsByName(fileType.getName());
    }
}
