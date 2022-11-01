package com.example.saaoa.service;

import com.example.saaoa.dto.SaleDetailRegistrationDto;
import com.example.saaoa.model.Sale_Detail;

import java.util.Collection;

public interface ISale_DetailService {
    Sale_Detail save(SaleDetailRegistrationDto registrationDto);

    Sale_Detail save(Long id, SaleDetailRegistrationDto registrationDto);


    void delete(Long id);
    void delete(Sale_Detail sd);
}
