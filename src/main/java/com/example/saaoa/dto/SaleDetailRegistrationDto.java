package com.example.saaoa.dto;

import com.example.saaoa.model.Product;
import com.example.saaoa.model.Sale;
import lombok.Getter;
import lombok.Setter;

public class SaleDetailRegistrationDto {

    @Getter@Setter
    private Sale sale;

    @Getter@Setter
    private Product product;

    @Getter@Setter
    private int quantity;

    public SaleDetailRegistrationDto() {
    }

    public SaleDetailRegistrationDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public SaleDetailRegistrationDto(Sale sale, Product product, int quantity) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
    }
}
