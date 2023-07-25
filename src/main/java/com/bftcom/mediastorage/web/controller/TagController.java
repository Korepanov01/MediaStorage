package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.dto.TagDto;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
import com.bftcom.mediastorage.model.request.tag.PutTagRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.TagService;
import com.bftcom.mediastorage.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TagDto> get(
            SearchStringParameters parameters) {
        List<Tag> tags = tagService.findByParameters(parameters);
        return tags
                .stream()
                .map(TagDto::ConvertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostTagRequest request) {
        Tag tag = PostTagRequest.convertToTag(request);

        try {
            tagService.save(tag);
        }
        catch (EntityAlreadyExistsException exception) {
            return Response.TagNameAlreadyExists;
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(tag);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(
            @PathVariable
            Long id) {

        try {
            tagService.delete(id);
        }
        catch (EntityNotFoundException exception) {
            return Response.TagNotFound;
        }

        return Response.Ok;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putTag(
            @PathVariable Long id,
            @Valid @RequestBody PutTagRequest request) {
        Tag tag = PutTagRequest.convertToTag(request);
        tag.setId(id);

        try {
            tagService.update(tag);
        } catch (EntityNotFoundException exception) {
            return Response.TagNotFound;
        } catch (EntityAlreadyExistsException exception) {
            return Response.TagNameAlreadyExists;
        }

        return Response.Ok;
    }
}
