package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.user.PostUserRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.UserService;
import com.bftcom.mediastorage.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> get(
            SearchStringParameters parameters) {
        List<User> roles = userService.findByParameters(parameters);
        return roles
                .stream()
                .map(UserDto::ConvertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostUserRequest request) {
        User user = PostUserRequest.convertToUser(request);

        try {
            userService.save(user);
        }
        catch (EntityAlreadyExistsException exception) {
            return Response.UserAlreadyExists;
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            userService.delete(id);
        }
        catch (EntityNotFoundException exception) {
            return Response.UserNotFound;
        }

        return Response.Ok;
    }
}
