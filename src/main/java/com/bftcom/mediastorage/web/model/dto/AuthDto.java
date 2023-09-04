package com.bftcom.mediastorage.web.model.dto;

import com.bftcom.mediastorage.web.security.AppUserDetails;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class AuthDto extends UserDto{

    private final String jwt;

    public AuthDto(@NonNull AppUserDetails userDetails, @NonNull List<String> roles, @NonNull String jwt) {
        super(userDetails.getId(), userDetails.getName(), userDetails.getEmail(), roles);
        this.jwt = jwt;
    }
}
