package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Media;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends CustomJpaRepository<Media> {

    @Query(
            "SELECT m FROM Media m \n" +
            "JOIN m.tags t \n" +
            "WHERE (:categoryIds IS NULL OR m.category.id IN :categoryIds) \n" +
            "AND (:tagIds IS NULL OR t.id IN :tagIds) \n" +
            "AND (:typeIds IS NULL OR m.mediaType.id IN :typeIds) \n" +
            "AND (:searchString IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :searchString, '%'))) \n" +
            "AND (:userId IS NULL OR m.user.id = :userId)")
    List<Media> findByParameters(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("tagIds") List<Long> tagIds,
            @Param("typeIds") List<Long> typeIds,
            @Param("searchString") String searchString,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
