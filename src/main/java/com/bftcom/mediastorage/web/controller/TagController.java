package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.exception.TagAlreadyExistsException;
import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.PagingParameters;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.PostTagRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.TagService;
import com.bftcom.mediastorage.web.BadResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> getTags(
            SearchStringParameters parameters) {
        List<Tag> tags = tagService.findByParameters(parameters);
        return tags.stream().map(TagDto::ConvertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> postTag(
            @Valid
            @RequestBody
            PostTagRequest request) {
        Tag tag = PostTagRequest.convertToTag(request);

        try {
            tagService.save(tag);
        }
        catch (TagAlreadyExistsException e) {
            return BadResponses.TagAlreadyExists;
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(tag);

        return ResponseEntity.ok(response);
    }
}
