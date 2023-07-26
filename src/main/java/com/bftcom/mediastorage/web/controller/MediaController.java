//package com.bftcom.mediastorage.web.controller;
//
//import com.bftcom.mediastorage.model.dto.TagDto;
//import com.bftcom.mediastorage.model.entity.Tag;
//import com.bftcom.mediastorage.model.parameters.MediaSearchParameters;
//import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
//import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
//import com.bftcom.mediastorage.model.request.tag.PutTagRequest;
//import com.bftcom.mediastorage.model.response.PostEntityResponse;
//import com.bftcom.mediastorage.service.MediaService;
//import com.bftcom.mediastorage.web.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/media")
//public class MediaController {
//
//    private final MediaService mediaService;
//
//    @Autowired
//    public MediaController(MediaService mediaService) {
//        this.mediaService = mediaService;
//    }
//
//    @GetMapping
//    public List<TagDto> getMedia(
//            MediaSearchParameters parameters) {
//        List<Tag> tags = mediaService.findByParameters(parameters);
//        return tags
//                .stream()
//                .map(TagDto::ConvertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping
//    public ResponseEntity<?> postTag(
//            @Valid
//            @RequestBody
//            PostTagRequest request) {
//        if (tagService.isTagNameExists(request.getName())) {
//            return Response.TagNameAlreadyExists;
//        }
//
//        Tag tag = PostTagRequest.convertToTag(request);
//        tagService.save(tag);
//
//        PostEntityResponse response = PostEntityResponse.convertFromEntity(tag);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> putTag(
//            @PathVariable Long id,
//            @Valid @RequestBody PutTagRequest request) {
//        if (tagService.isTagExists(id)) {
//            return Response.TagNotFound;
//        }
//
//        if (tagService.isTagNameExists(request.getName())) {
//            return Response.TagNameAlreadyExists;
//        }
//
//        Tag tag = PutTagRequest.convertToTag(request);
//        tag.setId(id);
//        tagService.update(tag);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTag(
//            @PathVariable Long id) {
//        Optional<Tag> optionalTag = tagService.findById(id);
//
//        if (optionalTag.isEmpty()) {
//            return Response.TagNotFound;
//        }
//
//        tagService.delete(optionalTag.get());
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
