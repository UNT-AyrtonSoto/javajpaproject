package com.example.saaoa.repository;

import com.example.saaoa.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<Sale, Long> {
}
