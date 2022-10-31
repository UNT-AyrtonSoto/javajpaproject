package com.example.saaoa.service;

import com.example.saaoa.dto.ProductRegistrationDto;
import com.example.saaoa.model.Product;
import com.example.saaoa.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product save(ProductRegistrationDto registrationDto){
        Product product = new Product(
                registrationDto.getName(),
                registrationDto.getDescription(),
                registrationDto.getPrice(),
                registrationDto.getStock()
        );
        return productRepository.save(product);
    }

    @Override
    public Product findByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> listAll(){return productRepository.findAll();}
}
