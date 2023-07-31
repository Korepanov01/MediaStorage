package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.RoleDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.role.PostRoleRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController implements FullController<
        RoleDto,
        Role,
        PostRoleRequest,
        SearchStringParameters> {

    private final RoleService roleService;

    @Override
    public RoleDto convertToDto(Role role) {
        return new RoleDto(role);
    }

    @Override
    public ParameterSearchService<Role, SearchStringParameters> getMainService() {
        return roleService;
    }
}
