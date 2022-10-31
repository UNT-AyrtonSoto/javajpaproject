package com.example.saaoa.dto;

import lombok.Getter;
import lombok.Setter;

public class SellerRegistrationDto {
    @Getter@Setter
    private String dni;

    @Getter@Setter
    private String firstName;

    @Getter@Setter
    private String lastName;

    @Getter@Setter
    private String email;

    public SellerRegistrationDto() {
    }

    public SellerRegistrationDto(String dni, String firstName, String lastName, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
