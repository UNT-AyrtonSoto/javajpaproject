package com.example.saaoa.service;

import com.example.saaoa.dto.ClientRegistrationDto;
import com.example.saaoa.model.Client;

import java.util.List;

public interface IClientService {
    Client save(ClientRegistrationDto registrationDto);

    Client findByDni(String dni);

    List<Client> listAll();

    void delete(Long id);
    void delete(Client client);
}
