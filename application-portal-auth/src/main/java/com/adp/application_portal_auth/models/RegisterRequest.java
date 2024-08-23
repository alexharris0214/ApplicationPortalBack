package com.adp.application_portal_auth.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}