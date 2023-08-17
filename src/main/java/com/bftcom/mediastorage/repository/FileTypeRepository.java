package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FileTypeRepository extends CrudRepository<FileType> {

    @Transactional(readOnly = true)
    Optional<FileType> findByName(@NonNull String name);

    @Transactional(readOnly = true)
    boolean existsByName(@NonNull String name);

    @Transactional(readOnly = true)
    List<FileType> findBySearchString(@NonNull String searchString);
}
