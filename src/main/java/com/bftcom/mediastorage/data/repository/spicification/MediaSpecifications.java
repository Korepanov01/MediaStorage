package com.bftcom.mediastorage.data.repository.spicification;

import com.bftcom.mediastorage.data.entity.Media;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.List;

public class MediaSpecifications {

    public static Specification<Media> findByParameters(
            List<Long> categoryIds,
            List<Long> tagIds,
            List<Long> typeIds,
            String searchString,
            Long userId,
            Boolean isRandom) {
        return (Root<Media> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate categoryPredicate = CollectionUtils.isEmpty(categoryIds)
                    ? builder.conjunction()
                    : root.get("category").get("id").in(categoryIds);

            Predicate tagPredicate = CollectionUtils.isEmpty(tagIds)
                    ? builder.conjunction()
                    : root.join("tags").get("id").in(tagIds);

            Predicate typePredicate = CollectionUtils.isEmpty(typeIds)
                    ? builder.conjunction()
                    : root.get("mediaType").get("id").in(typeIds);

            Predicate searchPredicate = !StringUtils.hasText(searchString)
                    ? builder.conjunction()
                    : builder.like(builder.lower(root.get("name")), "%" + searchString.toLowerCase() + "%");

            Predicate userPredicate = userId == null
                    ? builder.conjunction()
                    : builder.equal(root.get("user").get("id"), userId);

            Predicate finalPredicate = builder.and(categoryPredicate, tagPredicate, typePredicate, searchPredicate, userPredicate);

            if (BooleanUtils.isTrue(isRandom)) {
                query.orderBy(builder.asc(builder.function("RANDOM", null)));
            }

            return finalPredicate;
        };
    }
}
