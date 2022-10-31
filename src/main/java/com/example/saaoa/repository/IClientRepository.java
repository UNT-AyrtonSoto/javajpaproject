package com.example.saaoa.repository;

import com.example.saaoa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client,Long>{
    Client findByDni(String dni);
}
