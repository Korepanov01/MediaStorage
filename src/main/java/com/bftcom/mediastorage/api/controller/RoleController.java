package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.RoleDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.role.PostRoleRequest;
import com.bftcom.mediastorage.service.BaseService;
import com.bftcom.mediastorage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/roles")
public class RoleController extends BaseController<
        RoleDto,
        Role,
        PostRoleRequest,
        SearchStringParameters>{

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected RoleDto convertToDto(Role role) {
        return new RoleDto(role);
    }

    @Override
    protected BaseService<Role, SearchStringParameters> getMainService() {
        return roleService;
    }
}
