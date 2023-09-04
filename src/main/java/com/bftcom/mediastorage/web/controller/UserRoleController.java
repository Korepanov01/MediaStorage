package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.web.Responses;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserService userService;

    @PostMapping("/{id}/give_admin")
    public ResponseEntity<?> giveAdmin(
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
                userService.removeAdminRole(userId);
            else
                userService.addAdminRole(userId);
        }
        catch (EntityNotFoundException | EntityExistsException e) {
            return Responses.badRequest(e.getMessage());
        }

        return Responses.OK;
    }
}
