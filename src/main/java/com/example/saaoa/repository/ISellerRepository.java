package com.example.saaoa.repository;

import com.example.saaoa.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellerRepository extends JpaRepository<Seller,Long>{
    Seller findByDni(String dni);
}
