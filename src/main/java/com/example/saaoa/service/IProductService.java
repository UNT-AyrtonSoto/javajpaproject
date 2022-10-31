package com.example.saaoa.service;

import com.example.saaoa.dto.ProductRegistrationDto;
import com.example.saaoa.model.Sale;
import com.example.saaoa.model.Product;

import java.util.List;

public interface IProductService {
    Product save(ProductRegistrationDto registrationDto);

    Product findByName(String name);

    List<Product> listAll();
}
