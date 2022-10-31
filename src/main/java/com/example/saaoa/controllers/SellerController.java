package com.example.saaoa.controllers;

import com.example.saaoa.model.Seller;
import com.example.saaoa.service.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/sellers")
public class SellerController {
    @Autowired
    private SellerServiceImpl sellerService;

    private String list_template = "seller/list-seller";
    private String list_redirect = "redirect:/sellers/";

    @GetMapping("/")
    public String listSellers(Model model){
        List<Seller> sellers = sellerService.listAll();
        model.addAttribute("listSellers", sellers);

        return  list_template;
    }
}
