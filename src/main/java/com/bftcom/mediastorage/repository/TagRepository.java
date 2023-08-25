package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagRepository extends CustomJpaRepository<Tag> {

    List<Tag> findByNameContainsIgnoreCase(String searchString, Pageable pageable);
}
