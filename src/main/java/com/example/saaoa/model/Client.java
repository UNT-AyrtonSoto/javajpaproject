package com.example.saaoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "client", uniqueConstraints = @UniqueConstraint(columnNames = {"dni","email"}))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    @Getter@Setter
    private Long id;

    @Column(name = "dni")
    @Getter@Setter
    private String dni;

    @Column(name = "first_name")
    @Getter@Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter@Setter
    private String lastName;

    @Column(name = "email")
    @Getter@Setter
    private String email;

    @Column(name = "active")
    @Getter@Setter
    private boolean active;

    @OneToMany(mappedBy = "client")
    @Getter@Setter
    private Collection<Sale> purchases;

    public Client(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = true;
    }

    public Client(Long id, String firstName, String lastName, String email, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public Client(Long id, String dni, String firstName, String lastName, String email, boolean active, Collection<Sale> purchases) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.purchases = purchases;
    }

    public Client() {

    }

    public String toJSON(){
        return
            String.format("Client{id='{0}',dni='{1}',firstName='{2}',lastName='{3}',email='{4}',active='{0}'}", this.id, this.dni, this.firstName, this.lastName, this.email, this.active);
    }

    @Override
    public String toString(){
        return this.firstName +" "+this.lastName;
    }
}
