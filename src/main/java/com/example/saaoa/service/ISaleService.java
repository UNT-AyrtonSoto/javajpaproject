package com.example.saaoa.service;

import com.example.saaoa.dto.SaleRegistrationDto;
import com.example.saaoa.model.Sale;
import com.example.saaoa.model.Sale_Detail;
import com.example.saaoa.model.Seller;

import java.util.Collection;

public interface ISaleService {
    Sale save(SaleRegistrationDto registrationDto);

    Sale save(Long id, SaleRegistrationDto registrationDto);

    Sale save(SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_detail);

    Sale save(Long id, SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_detail);

    public void delete(Long id);
    public void delete(Sale sale);
}
