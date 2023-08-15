package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService extends ParameterSearchService<Tag, TagSearchParameters> {

    private final TagRepository tagRepository;

    public List<Tag> getByMediaId(@NonNull Long mediaId) {
        return tagRepository.getByMediaId(mediaId);
    }

    @Override
    protected ParametersSearchRepository<Tag, TagSearchParameters> getMainRepository() {
        return tagRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.findByName(tag.getName()).isPresent();
    }
}
