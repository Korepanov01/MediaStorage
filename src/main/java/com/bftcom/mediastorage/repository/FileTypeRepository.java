package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.FileType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {

    FileType findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);
}
