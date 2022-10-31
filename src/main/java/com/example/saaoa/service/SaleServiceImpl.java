package com.example.saaoa.service;

import com.example.saaoa.dto.SaleRegistrationDto;
import com.example.saaoa.model.Product;
import com.example.saaoa.model.Sale;
import com.example.saaoa.model.Sale_Detail;
import com.example.saaoa.repository.IClientRepository;
import com.example.saaoa.repository.ISaleRepository;
import com.example.saaoa.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SaleServiceImpl implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private ISellerRepository sellerRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public Sale save(SaleRegistrationDto registrationDto){
        Sale sale = new Sale(
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient()
        );
        return sale;
    }

    @Override
    public Sale save(SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_details){
        Sale sale = new Sale(
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient(),
                sale_details
        );
        return sale;
    }


    public List<Sale> listAll(){return saleRepository.findAll();}
}
