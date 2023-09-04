package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends CustomJpaRepository<Tag> {

    @Query("SELECT t FROM Tag t \n" +
            "WHERE (:searchString IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :searchString, '%')))")
    List<Tag> findByParameters(
            @Param("searchString") String searchString,
            Pageable pageable
    );
}
