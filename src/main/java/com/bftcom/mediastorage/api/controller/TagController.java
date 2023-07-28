package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.TagSearchParameters;
import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController extends GetByParametersController<
        TagDto,
        Tag,
        PostTagRequest,
        TagSearchParameters> {

    private final TagService tagService;

    @Override
    protected TagDto convertToDto(Tag tag) {
        return new TagDto(tag);
    }

    @Override
    protected ParameterSearchService<Tag, TagSearchParameters> getMainService() {
        return tagService;
    }
}
