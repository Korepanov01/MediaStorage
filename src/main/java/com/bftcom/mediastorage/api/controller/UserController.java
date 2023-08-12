package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.api.controller.interfaces.DeleteController;
import com.bftcom.mediastorage.api.controller.interfaces.ParametersSearchController;
import com.bftcom.mediastorage.exception.EmailAlreadyUsedException;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.NameAlreadyUsedException;
import com.bftcom.mediastorage.model.api.request.RegisterRequest;
import com.bftcom.mediastorage.model.api.response.PostEntityResponse;
import com.bftcom.mediastorage.model.dto.UserHeaderDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.service.ParameterSearchService;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements
        ParametersSearchController<UserHeaderDto, User, SearchStringParameters>,
        DeleteController<User> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid
            @RequestBody
            RegisterRequest request) {
        User user = request.covertToEntity(passwordEncoder);

        try {
            userService.register(user);
        } catch (EntityAlreadyExistsException e) {
            return Response.getEntityAlreadyExists("Пользователь уже существует");
        } catch (NameAlreadyUsedException e) {
            return Response.getUserNameAlreadyExists();
        } catch (EmailAlreadyUsedException e) {
            return Response.getEmailAlreadyExists();
        }

        return ResponseEntity.ok(new PostEntityResponse(user.getId()));
    }

    @Override
    public UserHeaderDto convertToListItemDto(User user) {
        return new UserHeaderDto(user);
    }

    @Override
    public ParameterSearchService<User, SearchStringParameters> getMainService() {
        return userService;
    }
}
