package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Responses;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.model.api.UpdateUserNameRequest;
import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.security.AuthParser;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements ParametersSearchController<UserDto, User, SearchStringParameters> {

    private final UserService userService;

    @PatchMapping("/update_name")
    public ResponseEntity<?> updateName(
            @Valid
            @RequestBody
            UpdateUserNameRequest request,
            Authentication authentication) {
        Optional<Long> optionalUserId = AuthParser.getUserId(authentication);
        if (optionalUserId.isEmpty()) {
            return Responses.UNAUTHORIZED;
        }

        try {
            userService.updateName(request.getName(), optionalUserId.get());
        } catch (EntityExistsException e) {
            return Responses.badRequest(e.getMessage());
        } catch (EntityNotFoundException e) {
            return Responses.notFound(e.getMessage());
        }

        return Responses.OK;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {

        try {
            userService.delete(id);
        } catch (IllegalOperationException e) {
            return Responses.badRequest(e.getMessage());
        } catch (EntityNotFoundException e) {
            return Responses.notFound(e.getMessage());
        }

        return Responses.OK;
    }

    @Override
    public UserDto convertToListItemDto(@NonNull User user) {
        return new UserDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
