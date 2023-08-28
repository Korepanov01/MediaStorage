package com.bftcom.mediastorage.security;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class AuthParser {

    public static Optional<Long> getUserId(@Nullable Authentication authentication) {
        Optional<AppUserDetails> optionalUserDetails = AuthParser.getUserDetails(authentication);
        return optionalUserDetails.map(AppUserDetails::getId);

    }

    public static Optional<AppUserDetails> getUserDetails(@Nullable Authentication authentication) {
        if (authentication == null || authentication.getPrincipal().getClass() != AppUserDetails.class) {
            return Optional.empty();
        }
        return Optional.ofNullable((AppUserDetails)authentication.getPrincipal());
    }
}
