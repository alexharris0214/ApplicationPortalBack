package com.alexharris.web_chat.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String userId;
    private String token;
}
