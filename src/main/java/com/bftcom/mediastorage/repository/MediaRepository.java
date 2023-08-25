package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

}
