package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.web.Responses;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.LoginException;
import com.bftcom.mediastorage.web.model.request.LoginRequest;
import com.bftcom.mediastorage.web.model.response.PostEntityResponse;
import com.bftcom.mediastorage.web.model.request.RegisterRequest;
import com.bftcom.mediastorage.web.model.dto.AuthDto;
import com.bftcom.mediastorage.data.entity.User;
import com.bftcom.mediastorage.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid
            @RequestBody
            LoginRequest request) {
        AuthService.LoginInfo loginInfo;
        try {
            loginInfo = authService.login(request.getEmail(), request.getPassword());
        } catch (LoginException e) {
            return Responses.badRequest(e.getMessage());
        }

        return ResponseEntity.ok(new AuthDto(
                loginInfo.getUserDetails(),
                loginInfo.getRoles(),
                loginInfo.getJwt()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid
            @RequestBody
            RegisterRequest request) {
        try {
            User user = authService.register(request.getName(), request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new PostEntityResponse(user.getId()));
        } catch (EntityExistsException e) {
            return Responses.badRequest(e.getMessage());
        }
    }
}