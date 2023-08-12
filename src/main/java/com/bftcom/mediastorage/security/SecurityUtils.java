package com.bftcom.mediastorage.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("securityUtils")
public class SecurityUtils {
    public boolean checkUserId(Authentication authentication, Long id) {
        if (!authentication.isAuthenticated()) {
            return false;
        }

        if (authentication.getPrincipal().getClass() != AppUserDetails.class) {
            return false;
        }

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        return authenticatedUserId.equals(id);
    }
}
