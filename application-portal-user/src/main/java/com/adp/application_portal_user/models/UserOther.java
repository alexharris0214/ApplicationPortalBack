package com.adp.application_portal_user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserOther {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private int age;
}
