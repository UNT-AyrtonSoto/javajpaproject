package com.example.saaoa.repository;

import com.example.saaoa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
