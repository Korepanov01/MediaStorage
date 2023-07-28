package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTypeService extends ParameterSearchService<FileType, SearchStringParameters> {

    private final FileTypeRepository fileTypeRepository;

    @Override
    protected ParametersSearchRepository<FileType, SearchStringParameters> getMainRepository() {
        return fileTypeRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull FileType fileType) {
        return fileTypeRepository.findByName(fileType.getName()).isPresent();
    }
}
