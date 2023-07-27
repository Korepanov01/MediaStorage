package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService extends BaseService<Tag, SearchStringParameters> {

    private final TagRepository tagRepository;

    @Override
    protected ParametersSearchRepository<Tag, SearchStringParameters> getMainRepository() {
        return tagRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.findByName(tag.getName()).isPresent();
    }

    public void update(Tag tag) throws EntityAlreadyExistsException, EntityNotFoundException {
        Optional<Tag> optionalTag = tagRepository.findById(tag.getId());

        if (optionalTag.isEmpty()) {
            throw new EntityNotFoundException();
        }

        if (isSameEntityExists(tag)) {
            throw new EntityAlreadyExistsException();
        }

        tagRepository.update(tag);
    }
}
