package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService extends ParameterSearchService<Tag, SearchStringParameters> {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findByParameters(SearchStringParameters parameters) {
        return tagRepository.findByParameters(
                parameters.getSearchString(),
                PageRequest.of(parameters.getPageIndex(), parameters.getPageSize()));
    }

    @Override
    protected CustomJpaRepository<Tag> getMainRepository() {
        return tagRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull Tag tag) {
        return tagRepository.existsByName(tag.getName());
    }
}
