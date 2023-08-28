package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MediaRepository extends CustomJpaRepository<Media> {

    List<Media> findAll(Specification<Media> specification, Pageable pageable);
}
