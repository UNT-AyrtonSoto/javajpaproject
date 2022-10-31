package com.example.saaoa.service;

import com.example.saaoa.dto.SellerRegistrationDto;
import com.example.saaoa.model.Seller;
import com.example.saaoa.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService{

    @Autowired
    private ISellerRepository sellerRepository;

    public Seller save(SellerRegistrationDto registrationDto){
        Seller seller = null;
        if(sellerRepository.findByDni(registrationDto.getDni())==null){
            seller = new Seller(
                    registrationDto.getDni(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail());
        }
        return sellerRepository.save(seller);
    }

    public Seller findByDni(String dni){
        return sellerRepository.findByDni(dni);
    }

    public List<Seller> listAll(){return sellerRepository.findAll();}
}
