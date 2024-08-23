package com.adp.application_portal_auth.models;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
