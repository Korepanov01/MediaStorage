package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.dto.RoleDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.role.PostRoleRequest;
import com.bftcom.mediastorage.model.response.PostEntityResponse;
import com.bftcom.mediastorage.service.RoleService;
import com.bftcom.mediastorage.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> get(
            SearchStringParameters parameters) {
        List<Role> roles = roleService.findByParameters(parameters);
        return roles
                .stream()
                .map(RoleDto::ConvertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> post(
            @Valid
            @RequestBody
            PostRoleRequest request) {
        Role role = PostRoleRequest.convertToTag(request);

        try {
            roleService.save(role);
        }
        catch (EntityAlreadyExistsException exception) {
            return Response.RoleNameAlreadyExists;
        }

        PostEntityResponse response = PostEntityResponse.convertFromEntity(role);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable
            Long id) {
        try {
            roleService.delete(id);
        }
        catch (EntityNotFoundException exception) {
            return Response.RoleNotFound;
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
