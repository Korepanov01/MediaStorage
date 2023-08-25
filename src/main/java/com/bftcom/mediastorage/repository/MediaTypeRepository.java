package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.MediaType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {

    MediaType findByName(@NonNull String name);
}
