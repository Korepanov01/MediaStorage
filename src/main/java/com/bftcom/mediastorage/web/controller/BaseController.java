//package com.bftcom.mediastorage.web.controller;
//
//import com.bftcom.mediastorage.model.entity.Tag;
//import com.bftcom.mediastorage.model.request.tag.PostTagRequest;
//import com.bftcom.mediastorage.model.request.tag.PutTagRequest;
//import com.bftcom.mediastorage.model.response.PostEntityResponse;
//import com.bftcom.mediastorage.web.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.Optional;
//
//@Controller
//public abstract class BaseController<T> {
//
//    private String asd;
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
