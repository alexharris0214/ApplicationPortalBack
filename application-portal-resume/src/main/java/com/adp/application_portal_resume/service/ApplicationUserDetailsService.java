package com.adp.application_portal_resume.service;

import com.adp.application_portal_resume.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private String userId;

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User ID not found: " + userId));
        setId(userId);
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("This service is designed to load users by userId, not username.");
    }

    public String getId(){
        return this.userId;
    }

    public void setId(String userId){
        this.userId = userId;
    }

}
