package com.bftcom.mediastorage.repository;

import com.bftcom.mediastorage.model.entity.Tag;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(@NonNull String name);
}
