package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.LoginException;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.repository.UserRepository;
import com.bftcom.mediastorage.security.AppUserDetails;
import com.bftcom.mediastorage.security.JwtUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public LoginInfo login(@NonNull String email, @NonNull String password) throws LoginException {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new LoginException("Неправильный логин или пароль!", e);
        }

        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new LoginInfo(jwt, userDetails, roles);
    }

    public User register(@NonNull String name, @NonNull String email, @NonNull String password) throws EntityExistsException {
        if (userRepository.existsByName(name)) {
            throw new EntityExistsException("Имя пользователя уже занято");
        }
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException("Почта уже занята");
        }

        User user = new User(name, passwordEncoder.encode(password), email);

        userRepository.save(user);

        return user;
    }

    @Data
    @RequiredArgsConstructor
    public static class LoginInfo {
        private final String jwt;
        private final AppUserDetails userDetails;
        private final List<String> roles;
    }
}
