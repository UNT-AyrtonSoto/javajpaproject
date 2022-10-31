package com.example.saaoa.service;

import com.example.saaoa.dto.ClientRegistrationDto;
import com.example.saaoa.model.Client;
import com.example.saaoa.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    public Client save(ClientRegistrationDto registrationDto){
        Client client = null;
        //if(clientRepository.findByDni(registrationDto.getDni())==null){
            client = new Client(
                    registrationDto.getDni(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail()
            );
        //}
        return client;
    }

    public Client findByDni(String dni){
        return clientRepository.findByDni(dni);
    }

    public List<Client> listAll(){return clientRepository.findAll();}
}
