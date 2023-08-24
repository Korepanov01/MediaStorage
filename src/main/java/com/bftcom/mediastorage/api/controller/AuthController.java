package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.api.Response;
import com.bftcom.mediastorage.model.api.request.LoginRequest;
import com.bftcom.mediastorage.model.api.request.RegisterRequest;
import com.bftcom.mediastorage.model.api.response.PostEntityResponse;
import com.bftcom.mediastorage.model.dto.AuthDto;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.security.AppUserDetails;
import com.bftcom.mediastorage.security.JwtUtils;
import com.bftcom.mediastorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Transactional
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid
            @RequestBody
            LoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(new AuthDto(userDetails, roles, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid
            @RequestBody
            RegisterRequest request) {
        User user = new User(request.getName(), request.getPassword(), request.getEmail());

        try {
            userService.register(user);
        } catch (EntityExistsException e) {
            return Response.getEntityAlreadyExists(e.getMessage());
        }

        return ResponseEntity.ok(new PostEntityResponse(user.getId()));
    }
}