package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.web.controller.interfaces.FullController;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.web.model.request.PostPutTagRequest;
import com.bftcom.mediastorage.web.model.dto.TagDto;
import com.bftcom.mediastorage.data.entity.Tag;
import com.bftcom.mediastorage.web.model.parameter.SearchStringParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.TagService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
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
    public void updateEntity(@NonNull Tag tag, @NonNull PostPutTagRequest postPutTagRequest) throws EntityExistsException {
        if (tagService.existsByName(postPutTagRequest.getName()))
            throw new EntityExistsException("Такая запись уже существует");

        tag.setName(postPutTagRequest.getName());
    }
}
