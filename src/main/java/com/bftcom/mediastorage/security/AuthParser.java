package com.bftcom.mediastorage.security;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class AuthParser {

    public static Optional<Long> getUserId(@Nullable Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal().getClass() != AppUserDetails.class) {
            return Optional.empty();
        }

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return Optional.ofNullable(userDetails.getId());
    }
}
