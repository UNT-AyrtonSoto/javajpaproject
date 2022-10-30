package com.example.saaoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Getter@Setter
    private Long id;

    @Column(name="name")
    @Getter@Setter
    private String name;

    @Column(name = "description")
    @Getter@Setter
    private String description;

    @Column(name = "price")
    @Getter@Setter
    private double price;

    @Column(name = "stock")
    @Getter@Setter
    private int stock;

    @Column(name = "active")
    @Getter@Setter
    private boolean active;

    @OneToMany(mappedBy = "product")
    @Getter@Setter
    private Collection<Sale_Detail> sales;

    public Product() {
    }

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = true;
    }

    public Product(Long id, String name, String description, double price, int stock, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = active;
    }

    public Product(Long id, String name, String description, double price, int stock, boolean active, Collection<Sale_Detail> sales) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = active;
        this.sales = sales;
    }
}
