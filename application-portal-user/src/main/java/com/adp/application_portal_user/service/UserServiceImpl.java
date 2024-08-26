package com.adp.application_portal_user.service;

import java.util.List;
import java.util.Optional;

import com.adp.application_portal_user.models.UserOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.application_portal_user.models.User;
import com.adp.application_portal_user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getSelf(String userId){
        return userRepository.findById(userId).orElse(null);
    }
    @Override
    public UserOther getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(this::buildUserOtherFromUser).orElse(null);
    }

    @Override
    public UserOther getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(this::buildUserOtherFromUser).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public UserOther buildUserOtherFromUser(User user){
        return UserOther.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .age(user.getAge())
                .build();
    }
}
