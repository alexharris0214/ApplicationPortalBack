package com.adp.application_portal_user.service;

import java.util.List;
import java.util.Optional;

import com.adp.application_portal_user.models.UserOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.application_portal_user.models.User;
import com.adp.application_portal_user.repository.UserRepository;

@Service
public interface UserService {
    public User createUser(User user);
    public User getSelf(String user);
    public UserOther getUserById(String id);
    public UserOther getUserByEmail(String email);
    public List<User> getAllUsers();
    public void deleteUserById(String id);
}
