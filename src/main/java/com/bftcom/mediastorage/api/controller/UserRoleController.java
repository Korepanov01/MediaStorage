package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.api.request.GiveRemoveAdminRequest;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.service.RoleService;
import com.bftcom.mediastorage.service.UserRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user_role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final RoleService roleService;

    @PostMapping("give_admin")
    public ResponseEntity<?> makeAdmin(
            @RequestBody
            @Valid
            GiveRemoveAdminRequest request) {

        return addDeleteRole(request.getUserId(), false);
    }

    @PostMapping("remove_admin")
    public ResponseEntity<?> removeAdmin(
            @RequestBody
            @Valid
            GiveRemoveAdminRequest request) {

        return addDeleteRole(request.getUserId(), true);
    }

    private ResponseEntity<?> addDeleteRole(@NonNull Long userId, boolean isRemove) {
        Role adminRole = roleService.findByName(Role.ADMIN).orElseThrow();
        try {
            if (isRemove) {
                userRoleService.deleteRole(userId, adminRole.getId());
            } else {
                userRoleService.addRole(userId, adminRole.getId());
            }
        } catch (EntityNotFoundException exception) {
            return Response.getEntityNotFound(exception.getMessage());
        } catch (EntityAlreadyExistsException exception) {
            return Response.getEntityAlreadyExists(exception.getMessage());
        }

        return Response.getOk();
    }
}
