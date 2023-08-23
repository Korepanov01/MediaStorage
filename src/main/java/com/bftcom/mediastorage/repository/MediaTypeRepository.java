package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaType;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface MediaTypeRepository extends CrudRepository<MediaType> {
    @Transactional(readOnly = true)
    MediaType findByName(@NonNull String name);
}
