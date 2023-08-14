package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.DeleteController;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.model.api.request.UpdateUserNameRequest;
import com.bftcom.mediastorage.model.dto.UserHeaderDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.security.AuthParser;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements
        ParametersSearchController<UserHeaderDto, User, SearchStringParameters>,
        DeleteController<User> {

    private final UserService userService;

    @PatchMapping("/update_name")
    public ResponseEntity<?> updateName(
            @Valid
            @RequestBody
            UpdateUserNameRequest request,
            Authentication authentication) {
        Optional<Long> optionalUserId = AuthParser.getUserId(authentication);
        if (optionalUserId.isEmpty()) {
            return Response.getUnauthorized();
        }

        try {
            userService.updateName(request.getName(), optionalUserId.get());
        } catch (EntityAlreadyExistsException e) {
            return Response.getEntityAlreadyExists(e.getMessage());
        }

        return Response.getOk();
    }

    @Override
    public UserHeaderDto convertToListItemDto(User user) {
        return new UserHeaderDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
