package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.GetByIdController;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.model.dto.RoleDto;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.searchparameters.RoleSearchParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController implements
        ParametersSearchController<RoleDto, Role, RoleSearchParameters>,
        GetByIdController<RoleDto, Role> {

    private final RoleService roleService;

    @Override
    public RoleDto convertToListItemDto(Role role) {
        return new RoleDto(role);
    }

    @Override
    public RoleDto convertToDto(Role role) {
        return new RoleDto(role);
    }

    @Override
    public ParameterSearchService<Role, RoleSearchParameters> getMainService() {
        return roleService;
    }
}
