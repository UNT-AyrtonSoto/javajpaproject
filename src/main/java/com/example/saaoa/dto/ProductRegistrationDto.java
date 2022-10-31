package com.example.saaoa.dto;

import lombok.Getter;
import lombok.Setter;

public class ProductRegistrationDto {

    @Getter@Setter
    private String name;

    @Getter@Setter
    private String description;

    @Getter@Setter
    private double price;

    @Getter@Setter
    private int stock;

    public ProductRegistrationDto() {
    }

    public ProductRegistrationDto(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
