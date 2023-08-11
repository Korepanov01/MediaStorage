package com.bftcom.mediastorage.model.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class JwtResponse {

    private final String jwt;
    private final Long id;
    private final String name;
    private final String email;
    private final List<String> roles;
}
