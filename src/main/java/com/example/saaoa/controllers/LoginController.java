package com.example.saaoa.controllers;

import com.example.saaoa.dto.UserRegistrationDto;
import com.example.saaoa.model.User;
import com.example.saaoa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/")
    public String home(Model model){return "index";}
    @GetMapping("/register")
    public String register(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("userRegistrationDto", userRegistrationDto);

        User userExists = userService.findByUsername(userRegistrationDto.getUserName());


        if (userExists != null) {
            return "redirect:/register?username";
        }
        if(result.hasErrors()){
            return "admin/auth/register";
        }
        userService.save(userRegistrationDto, "ROLE_USER");
        return "redirect:/register?success";
    }
    @GetMapping("/tables")
    public String getTables(Model model){
        return "dashboard/tables";
    }
}
