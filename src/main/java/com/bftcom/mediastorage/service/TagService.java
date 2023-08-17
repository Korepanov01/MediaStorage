package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService extends ParameterSearchService<Tag, SearchStringParameters> {

    private final TagRepository tagRepository;

    public List<Tag> getByMediaId(@NonNull Long mediaId) {
        return tagRepository.findByMediaId(mediaId);
    }

    @Override
    protected ParametersSearchRepository<Tag, SearchStringParameters> getMainRepository() {
        return tagRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.findByName(tag.getName()).isPresent();
    }
}
