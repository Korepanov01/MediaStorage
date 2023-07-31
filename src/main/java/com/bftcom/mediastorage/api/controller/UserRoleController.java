package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.request.delete.DeleteUserRoleRequest;
import com.bftcom.mediastorage.model.request.post.PostUserRoleRequest;
import com.bftcom.mediastorage.service.UserRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user_role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody
            @Valid
            DeleteUserRoleRequest request) {

        return addDeleteRole(request.getUserId(), request.getRoleId(), true);
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody
            @Valid
            PostUserRoleRequest request) {

        return addDeleteRole(request.getUserId(), request.getRoleId(), false);
    }

    private ResponseEntity<?> addDeleteRole(@NonNull Long userId, @NonNull Long roleId, boolean isDelete) {
        try {
            if (isDelete) {
                userRoleService.deleteRole(userId, roleId);
            } else {
                userRoleService.addRole(userId, roleId);
            }
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return Response.getOk();
    }
}
