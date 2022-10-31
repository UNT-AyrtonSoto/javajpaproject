package com.example.saaoa;

import com.example.saaoa.dto.ClientRegistrationDto;
import com.example.saaoa.dto.ProductRegistrationDto;
import com.example.saaoa.dto.SellerRegistrationDto;
import com.example.saaoa.dto.UserRegistrationDto;
import com.example.saaoa.model.Role;
import com.example.saaoa.model.User;
import com.example.saaoa.model.Seller;
import com.example.saaoa.model.Client;
import com.example.saaoa.model.Product;
import com.example.saaoa.repository.IRoleRepository;
import com.example.saaoa.repository.IUserRepository;
import com.example.saaoa.repository.ISellerRepository;
import com.example.saaoa.repository.IClientRepository;
import com.example.saaoa.repository.IProductRepository;
import com.example.saaoa.service.IUserService;
import com.example.saaoa.service.ISellerService;
import com.example.saaoa.service.IClientService;
import com.example.saaoa.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISellerRepository sellerRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISellerService sellerService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IProductService productService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createUserIfNotFound(new UserRegistrationDto("admin","admin","admin@admin.com","123456"), "ROLE_ADMIN");
        createUserIfNotFound(new UserRegistrationDto("Ayrton","Soto","ventas@admin.com","123456"), "ROLE_USER");
        createSellerIfNotFound(new SellerRegistrationDto("72737856", "Ayrton", "Soto", "ayrtonxd.123@gmail.com"));
        createClientIfNotFound(new ClientRegistrationDto("72737851", "Alenda", "Soto", "alendita.1503@gmail.com"));
        createProductIfNotFound(new ProductRegistrationDto( "Cappuccino", "Contiene expreso, vapor y espuma de leche a partes iguales", 17.5,12));
        createProductIfNotFound(new ProductRegistrationDto( "Empanada de carne", "Contiene carne de res, cebolla, huevo duro picado, aceitunas", 7,8));
        createProductIfNotFound(new ProductRegistrationDto( "Keke de naranja", "torta/biscocho sabor a naranja", 5,10));
        createProductIfNotFound(new ProductRegistrationDto( "Pan con pollo", "Contiene pollo desilachado, lechuga, tomates", 12,12));

    }
    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
    @Transactional
    User createUserIfNotFound(UserRegistrationDto registrationDto, String role) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            user = userService.save(registrationDto, role);
        }
        return user;
    }

    @Transactional
    Seller createSellerIfNotFound(SellerRegistrationDto registrationDto){
        Seller seller = sellerRepository.findByDni(registrationDto.getDni());
        if (seller == null) {
        }
        seller = sellerService.save(registrationDto);
        return seller;
    }

    @Transactional
    Client createClientIfNotFound(ClientRegistrationDto registrationDto){
        Client client = clientRepository.findByDni(registrationDto.getDni());
        if(client == null){
        }
        client = clientService.save(registrationDto);
        return client;
    }

    @Transactional
    Product createProductIfNotFound(ProductRegistrationDto registrationDto){
        Product product = productRepository.findByName(registrationDto.getName());
        if(product == null){
            product = productService.save(registrationDto);
        }
        return product;
    }
}
