package com.adp.application_portal_auth.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String userId;
    private String token;
}
