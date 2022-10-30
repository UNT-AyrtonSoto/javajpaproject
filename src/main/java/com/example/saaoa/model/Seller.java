package com.example.saaoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "seller", uniqueConstraints = @UniqueConstraint(columnNames = {"dni","email"}))
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Seller_id")
    @Getter@Setter
    private Long id;

    private String dni;

    private String firstName;

    private String lastName;

    private String email;

    private boolean active;

    @OneToMany(mappedBy = "seller")
    @Getter@Setter
    private Collection<Sale> sales;

    public Seller() {
    }

    public Seller(String dni, String firstName, String lastName, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = true;
    }

    public Seller(Long id, String dni, String firstName, String lastName, String email, boolean active) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public Seller(Long id, String dni, String firstName, String lastName, String email, boolean active, Collection<Sale> sales) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.sales = sales;
    }

    public String toJSON(){
        return
                String.format("Seller{id='{0}',dni='{1}',firstName='{2}',lastName='{3}',email='{4}',active='{0}'}", this.id, this.dni, this.firstName, this.lastName, this.email, this.active);
    }

    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}
