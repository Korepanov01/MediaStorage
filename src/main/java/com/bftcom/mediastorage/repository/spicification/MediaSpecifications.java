package com.bftcom.mediastorage.repository.spicification;

import com.bftcom.mediastorage.model.entity.Media;
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
        return (Root<Media> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate categoryPredicate = CollectionUtils.isEmpty(categoryIds)
                    ? criteriaBuilder.conjunction()
                    : root.get("category").get("id").in(categoryIds);

            Predicate tagPredicate = CollectionUtils.isEmpty(tagIds)
                    ? criteriaBuilder.conjunction()
                    : root.join("tags").get("id").in(tagIds);

            Predicate typePredicate = CollectionUtils.isEmpty(typeIds)
                    ? criteriaBuilder.conjunction()
                    : root.get("mediaType").get("id").in(typeIds);

            Predicate searchPredicate = !StringUtils.hasText(searchString)
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchString.toLowerCase() + "%");

            Predicate userPredicate = userId == null
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.equal(root.get("user").get("id"), userId);

            Predicate finalPredicate = criteriaBuilder.and(categoryPredicate, tagPredicate, typePredicate, searchPredicate, userPredicate);

            if (BooleanUtils.isTrue(isRandom)) {
                //TODO
            }

            return finalPredicate;
        };
    }
}
