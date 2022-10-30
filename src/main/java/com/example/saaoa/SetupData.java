package com.example.saaoa;

import com.example.saaoa.dto.UserRegistrationDto;
import com.example.saaoa.model.Role;
import com.example.saaoa.model.User;
import com.example.saaoa.repository.IRoleRepository;
import com.example.saaoa.repository.IUserRepository;
import com.example.saaoa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;



    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createUserIfNotFound(new UserRegistrationDto("admin","admin","admin@admin.com","123456"), "ROLE_ADMIN");
        createUserIfNotFound(new UserRegistrationDto("Ayrton","Soto","ventas@admin.com","123456"), "ROLE_USER");
    }
    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
    @Transactional
    User createUserIfNotFound(UserRegistrationDto registrationDto, String role) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            user = userService.save(registrationDto, role);
        }
        return user;
    }
}
