package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.DeleteController;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.api.controller.interfaces.PutController;
import com.bftcom.mediastorage.model.api.request.PutUserRequest;
import com.bftcom.mediastorage.model.dto.UserHeaderDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements
        ParametersSearchController<UserHeaderDto, User, SearchStringParameters>,
        DeleteController<User>,
        PutController<User, PutUserRequest> {

    private final UserService userService;

    @Override
    public UserHeaderDto convertToListItemDto(User user) {
        return new UserHeaderDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
