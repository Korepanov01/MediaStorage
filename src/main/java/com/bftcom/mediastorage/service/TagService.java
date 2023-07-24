package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.TagAlreadyExistsException;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findByParameters(SearchStringParameters parameters) {
        return tagRepository.findByParameters(parameters);
    }

    public Tag save(Tag tag) throws TagAlreadyExistsException {
        if (tagRepository.findByName(tag.getName()).isPresent())
            throw new TagAlreadyExistsException();
        return tagRepository.save(tag);
    }

    public void update(Tag tag) {
        tagRepository.update(tag);
    }

    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }
}
