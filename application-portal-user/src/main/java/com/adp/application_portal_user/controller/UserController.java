package com.adp.application_portal_user.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.adp.application_portal_user.models.UserOther;
import com.adp.application_portal_user.service.JwtService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adp.application_portal_user.models.User;
import com.adp.application_portal_user.service.UserService;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired 
    public UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public ResponseEntity<User> getSelf(@RequestHeader HttpHeaders headers){
        String token = Objects.requireNonNull(headers.getFirst("Authorization")).substring(7);
        String userId = jwtService.extractUsername(token);
        return ResponseEntity.ok(userService.getSelf(userId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserOther> getUserById(@PathVariable String id) {
        UserOther user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserOther> getUserByEmail(@PathVariable String email) {
        UserOther user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}
