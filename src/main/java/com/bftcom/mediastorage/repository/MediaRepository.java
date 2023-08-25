package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends CustomJpaRepository<Media> {

    @Query("SELECT m FROM Media m " +
            "JOIN m.tags t " +
            "WHERE (:categoryIds IS NULL OR m.category.id IN :categoryIds) " +
            "AND (:tagIds IS NULL OR t.id IN :tagIds) " +
            "AND (:typeIds IS NULL OR m.mediaType.id IN :typeIds) " +
            "AND (:searchString IS NULL OR LOWER(m.name) LIKE LOWER(concat('%', :searchString, '%'))) " +
            "AND (:userId IS NULL OR m.user.id = :userId) " +
            "ORDER BY CASE WHEN :randomOrder THEN RAND() ELSE m.id END")
    List<Media> findByParameters(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("tagIds") List<Long> tagIds,
            @Param("typeIds") List<Long> typeIds,
            @Param("searchString") String searchString,
            @Param("userId") Long userId,
            @Param("randomOrder") boolean randomOrder,
            Pageable pageable
    );
}
