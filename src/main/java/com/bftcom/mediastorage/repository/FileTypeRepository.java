package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;

public interface FileTypeRepository extends ParametersSearchRepository<FileType, SearchStringParameters>,
        NameSearchRepository<FileType>  {
}
