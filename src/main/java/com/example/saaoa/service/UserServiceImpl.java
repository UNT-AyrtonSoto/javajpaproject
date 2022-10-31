package com.example.saaoa.service;

import com.example.saaoa.dto.UserRegistrationDto;
import com.example.saaoa.model.Role;
import com.example.saaoa.model.User;
import com.example.saaoa.repository.IRoleRepository;
import com.example.saaoa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }



    @Override
    public User save(UserRegistrationDto registrationDto, String role) {
        User user = new User(
                registrationDto.getFirstName()
                ,registrationDto.getLastName()
                , registrationDto.getUserName()
                ,passwordEncoder.encode(registrationDto.getPassword())
                , List.of(roleRepository.findByName(role))
        );

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByEmail(username);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        System.out.println(user.toString());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
