package com.example.saaoa.service;

import com.example.saaoa.dto.SaleDetailRegistrationDto;
import com.example.saaoa.model.Sale_Detail;
import com.example.saaoa.repository.ISaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sale_DetailServiceImpl implements ISale_DetailService{

    @Autowired
    private ISaleDetailRepository saleDetailRepository;

    public Sale_Detail save(SaleDetailRegistrationDto registrationDto){
        Sale_Detail sale_detail = new Sale_Detail(
                registrationDto.getSale(),
                registrationDto.getProduct(),
                registrationDto.getQuantity()
        );
        return saleDetailRepository.save(sale_detail);
    };

    public Sale_Detail save(Long id, SaleDetailRegistrationDto registrationDto){
        Sale_Detail sale_detail = new Sale_Detail(
                id,
                registrationDto.getSale(),
                registrationDto.getProduct(),
                registrationDto.getQuantity()
        );
        return saleDetailRepository.save(sale_detail);
    };

    public void delete(Long id){

        saleDetailRepository.deleteById(id);
    }
    public void delete(Sale_Detail sd){
        saleDetailRepository.delete(sd);
    }
}
