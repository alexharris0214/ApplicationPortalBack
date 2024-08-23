package com.adp.application_portal_user.service;

import java.util.List;
import java.util.Optional;

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
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'createUser'");
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(String id) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'deleteUserById'");
        userRepository.deleteById(id);
    }

}
