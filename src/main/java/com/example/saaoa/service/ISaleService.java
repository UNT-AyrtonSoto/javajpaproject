package com.example.saaoa.service;

import com.example.saaoa.dto.SaleRegistrationDto;
import com.example.saaoa.model.Sale;
import com.example.saaoa.model.Sale_Detail;

import java.util.Collection;

public interface ISaleService {
    Sale save(SaleRegistrationDto registrationDto);

    Sale save(SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_detail);
}
