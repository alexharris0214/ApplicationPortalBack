package com.alexharris.web_chat.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class RegisterRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Role role;
}