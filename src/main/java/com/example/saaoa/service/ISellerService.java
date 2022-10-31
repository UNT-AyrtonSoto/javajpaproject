package com.example.saaoa.service;

import com.example.saaoa.dto.SellerRegistrationDto;
import com.example.saaoa.model.Product;
import com.example.saaoa.model.Seller;

import java.util.List;

public interface ISellerService {
    Seller save(SellerRegistrationDto registrationDto);

    Seller findByDni(String dni);

    List<Seller> listAll();
}
