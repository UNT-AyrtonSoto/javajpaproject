package com.example.saaoa.dto;

import com.example.saaoa.model.Client;
import com.example.saaoa.model.Seller;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class SaleRegistrationDto {

    @Getter@Setter
    private Client client;

    @Getter@Setter
    private Seller seller;

    @Getter@Setter
    private Date date;

    public SaleRegistrationDto(){}

    public SaleRegistrationDto(Client client, Seller seller, Date date) {
        this.client = client;
        this.seller = seller;
        this.date = date;
    }
}
