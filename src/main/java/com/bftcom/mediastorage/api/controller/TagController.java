package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.api.request.PostPutTagRequest;
import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.TagService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@Transactional
public class TagController implements FullController<
        TagDto,
        TagDto,
        Tag,
        PostPutTagRequest,
        PostPutTagRequest,
        SearchStringParameters> {

    private final TagService tagService;

    @Override
    public TagDto convertToListItemDto(@NonNull Tag tag) {
        return new TagDto(tag);
    }

    @Override
    public TagDto convertToDto(@NonNull Tag tag) {
        return new TagDto(tag);
    }

    @Override
    public ParameterSearchService<Tag, SearchStringParameters> getMainService() {
        return tagService;
    }

    @Override
    public Tag fillEntity(@NonNull PostPutTagRequest postPutTagRequest) {
        return new Tag(postPutTagRequest.getName());
    }

    @Override
    public void updateEntity(@NonNull Tag tag, @NonNull PostPutTagRequest postPutTagRequest) {
        tag.setName(postPutTagRequest.getName());
    }
}
