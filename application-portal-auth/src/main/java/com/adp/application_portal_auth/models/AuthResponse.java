package com.adp.application_portal_auth.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AuthResponse {
    private String userId;
    private String token;
    private Role role;
}
