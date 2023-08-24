package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Transactional
public class UserRoleController {

    private final UserService userService;

    @PostMapping("/{id}/give_admin")
    public ResponseEntity<?> makeAdmin(
            @PathVariable
            Long id) {
        return addDeleteRole(id, false);
    }

    @PostMapping("/{id}/remove_admin")
    public ResponseEntity<?> removeAdmin(
            @PathVariable
            Long id) {
        return addDeleteRole(id, true);
    }

    private ResponseEntity<?> addDeleteRole(@NonNull Long userId, boolean isRemove) {
        try {
            if (isRemove)
                userService.addAdminRole(userId);
            else
                userService.removeAdminRole(userId);
        }
        catch (EntityNotFoundException | EntityExistsException e) {
            return Response.getBadRequest(e.getMessage());
        }

        return Response.getOk();
    }
}
