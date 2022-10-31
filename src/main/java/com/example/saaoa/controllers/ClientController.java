package com.example.saaoa.controllers;

import com.example.saaoa.model.Client;
import com.example.saaoa.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    private String list_template = "client/list-client";
    private String list_redirect = "redirect:/clients/";

    @GetMapping("/")
    public String listClients(Model model){
        List<Client> clients = clientService.listAll();
        model.addAttribute("listClients", clients);

        return  list_template;
    }
}
