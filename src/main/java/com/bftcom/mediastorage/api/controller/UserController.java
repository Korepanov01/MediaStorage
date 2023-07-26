package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.dto.UserDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.model.request.user.PostUserRequest;
import com.bftcom.mediastorage.service.BaseService;
import com.bftcom.mediastorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<
        UserDto,
        User,
        PostUserRequest,
        SearchStringParameters>{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected UserDto convertToDto(User user) {
        return new UserDto(user);
    }

    @Override
    protected BaseService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
