package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService extends ParameterSearchService<Tag, TagSearchParameters> {

    private final TagRepository tagRepository;

    @Override
    protected ParametersSearchRepository<Tag, TagSearchParameters> getMainRepository() {
        return tagRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.findByName(tag.getName()).isPresent();
    }
}
