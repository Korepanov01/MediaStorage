package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.controller.interfaces.FullController;
import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.user.PostUserRequest;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements FullController<
        UserDto,
        User,
        PostUserRequest,
        SearchStringParameters> {

    private final UserService userService;

    @Override
    public UserDto convertToDto(User user) {
        return new UserDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
