package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
import com.bftcom.mediastorage.service.CrudService;
import com.bftcom.mediastorage.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController extends CrudController<
        TagDto,
        Tag,
        PostTagRequest,
        SearchStringParameters> {

    private final TagService tagService;

    @Override
    protected TagDto convertToDto(Tag tag) {
        return new TagDto(tag);
    }

    @Override
    protected CrudService<Tag, SearchStringParameters> getMainService() {
        return tagService;
    }
}
