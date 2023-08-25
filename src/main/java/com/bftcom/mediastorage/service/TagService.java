package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService extends CrudService<Tag> {

    private final TagRepository tagRepository;

    @Override
    protected CustomJpaRepository<Tag> getMainRepository() {
        return tagRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.existsByName(tag.getName());
    }
}
