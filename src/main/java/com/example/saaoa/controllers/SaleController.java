package com.example.saaoa.controllers;

import com.example.saaoa.model.Product;
import com.example.saaoa.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

public class SaleController {
    @Autowired
    private ProductServiceImpl productService;

    private String list_template = "product/list-product";
    private String list_redirect = "redirect:/product/";

    @GetMapping("/")
    public String listProducts(Model model){
        List<Product> products = productService.listAll();
        model.addAttribute("listProducts", products);

        return  list_template;
    }
}
