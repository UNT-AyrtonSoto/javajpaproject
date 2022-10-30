package com.example.saaoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sale_detail")
public class Sale_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    @Getter@Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @Getter@Setter
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter@Setter
    private Product product;

    @Column(name = "quantity")
    @Getter@Setter
    private int quantity;

    public Sale_Detail() {
    }

    public Sale_Detail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Sale_Detail(Sale sale, Product product, int quantity) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
    }

    public Sale_Detail(Long id, Sale sale, Product product, int quantity) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
    }
}
