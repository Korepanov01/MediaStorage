package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.dto.RoleDto;
import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.user.AddDeleteRoleRequest;
import com.bftcom.mediastorage.model.request.user.PostUserRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.RoleService;
import com.bftcom.mediastorage.service.UserRoleService;
import com.bftcom.mediastorage.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements FullController<
        UserDto,
        User,
        PostUserRequest,
        SearchStringParameters> {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    @GetMapping("/{id}/roles")
    public List<RoleDto> getRoles(
            @PathVariable
            Long id) {
        List<Role> roles = roleService.getUserRoles(id);
        return roles
                .stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/roles")
    public ResponseEntity<?> deleteRole(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            AddDeleteRoleRequest request) {

        return addDeleteRole(id, request, true);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<?> addRole(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            AddDeleteRoleRequest request) {

        return addDeleteRole(id, request, false);
    }

    private ResponseEntity<?> addDeleteRole(@NonNull Long id, @NonNull AddDeleteRoleRequest request, boolean isDelete) {
        try {
            if (isDelete) {
                userRoleService.deleteRole(id, request.getRoleId());
            } else {
                userRoleService.addRole(id, request.getRoleId());
            }
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return Response.getOk();
    }

    @Override
    public UserDto convertToDto(User user) {
        return new UserDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
