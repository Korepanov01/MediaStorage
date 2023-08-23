package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface FileTypeRepository extends CrudRepository<FileType> {

    @Transactional(readOnly = true)
    FileType findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);
}
