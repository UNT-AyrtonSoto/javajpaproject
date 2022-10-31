package com.example.saaoa.repository;

import com.example.saaoa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);
}
