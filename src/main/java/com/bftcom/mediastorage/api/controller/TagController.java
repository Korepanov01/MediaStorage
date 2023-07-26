package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
import com.bftcom.mediastorage.service.BaseService;
import com.bftcom.mediastorage.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController extends BaseController<
        TagDto,
        Tag,
        PostTagRequest,
        SearchStringParameters>{

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    protected TagDto convertToDto(Tag tag) {
        return new TagDto(tag);
    }

    @Override
    protected BaseService<Tag, SearchStringParameters> getMainService() {
        return tagService;
    }
}
