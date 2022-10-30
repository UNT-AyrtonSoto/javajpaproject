package com.example.saaoa.service;

import com.example.saaoa.dto.UserRegistrationDto;
import com.example.saaoa.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto, String role);
    User findByUsername(String username);

}
