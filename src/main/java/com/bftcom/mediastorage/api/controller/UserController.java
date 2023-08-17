package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.api.request.UpdateUserNameRequest;
import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.security.AuthParser;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.RoleService;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements
        ParametersSearchController<UserDto, User, SearchStringParameters> {

    private final UserService userService;
    private final RoleService roleService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        List<Role> roles = roleService.findByUserId(id);
        if (roles.stream().anyMatch(role -> role.getName().equals(Role.SUPER_ADMIN)))
            return Response.getBadRequest("Данного пользователя нельзя удалить");

        try {
            getMainService().delete(id);
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        }

        return Response.getOk();
    }

    @Override
    public UserDto convertToListItemDto(User user) {
        List<Role> roles = roleService.findByUserId(user.getId());
        return new UserDto(user, roles.stream().map(role -> "ROLE_" + role.getName()).collect(Collectors.toList()));
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
