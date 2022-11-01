package com.example.saaoa.service;

import com.example.saaoa.dto.SellerRegistrationDto;
import com.example.saaoa.model.Sale;
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
        Seller seller = sellerRepository.findByDni(registrationDto.getDni());
        if(seller==null){
            seller = new Seller(
                    registrationDto.getDni(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail());
        }else{
            seller.setDni(registrationDto.getDni());
            seller.setFirstName(registrationDto.getFirstName());
            seller.setLastName(registrationDto.getLastName());
            seller.setEmail(registrationDto.getEmail());
        }
        return sellerRepository.save(seller);
    }

    public Seller findByDni(String dni){
        return sellerRepository.findByDni(dni);
    }

    public List<Seller> listAll(){return sellerRepository.findAll();}

    public void delete(Long id){
        sellerRepository.deleteById(id);
    }
    public void delete(Seller seller){
        sellerRepository.delete(seller);
    }
}
