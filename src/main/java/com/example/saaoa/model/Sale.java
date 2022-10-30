package com.example.saaoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    @Getter@Setter
    private Long id;

    private Date saleDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @Getter@Setter
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter@Setter
    private Client client;

    @OneToMany(mappedBy = "sale")
    private Collection<Sale_Detail> items;

    public Sale(Date saleDate, Seller seller, Client client) {
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
    }

    public Sale(Date saleDate, Seller seller, Client client, Collection<Sale_Detail> items) {
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.items = items;
    }

    public Sale(Long id, Date saleDate, Seller seller, Client client, Collection<Sale_Detail> items) {
        this.id = id;
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.items = items;
    }
}

