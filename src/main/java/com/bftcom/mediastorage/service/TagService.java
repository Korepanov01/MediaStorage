package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements IService<Tag> {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public boolean isTagExists(Long id) {
        return tagRepository.findById(id).isPresent();
    }

    public boolean isTagNameExists(String name) {
        return tagRepository.findByName(name).isPresent();
    }

    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findByParameters(SearchStringParameters parameters) {
        return tagRepository.findByParameters(parameters);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public void update(Tag tag) {
        tagRepository.update(tag);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }
}
