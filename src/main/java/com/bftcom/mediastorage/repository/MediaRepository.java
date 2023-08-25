package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends CustomJpaRepository<Media> {

    @Query("SELECT m FROM Media m" +
            "\nJOIN m.tags t" +
            "\nWHERE (:categoryIds IS NULL OR m.category.id IN :categoryIds)" +
            "\nAND (:tagIds IS NULL OR t.id IN :tagIds)" +
            "\nAND (:typeIds IS NULL OR m.mediaType.id IN :typeIds)" +
            "\nAND (:searchString IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :searchString, '%')))" +
            "\nAND (:userId IS NULL OR m.user.id = :userId)" +
            "\nORDER BY CASE WHEN :randomSeed IS NOT NULL THEN :randomSeed ELSE m.name END")
    List<Media> findByParameters(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("tagIds") List<Long> tagIds,
            @Param("typeIds") List<Long> typeIds,
            @Param("searchString") String searchString,
            @Param("userId") Long userId,
            @Param("randomSeed") Integer randomSeed,
            Pageable pageable
    );
}
