package com.example.saaoa.service;

import com.example.saaoa.dto.ClientRegistrationDto;
import com.example.saaoa.model.Client;
import com.example.saaoa.model.Seller;
import com.example.saaoa.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    public Client save(ClientRegistrationDto registrationDto){
        Client client = clientRepository.findByDni(registrationDto.getDni());
        if(client==null){
            client = new Client(
                    registrationDto.getDni(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail()
            );
        }else{
            client.setDni(registrationDto.getDni());
            client.setFirstName(registrationDto.getFirstName());
            client.setLastName(registrationDto.getLastName());
            client.setEmail(registrationDto.getEmail());
        }
        return clientRepository.save(client);
    }

    public Client findByDni(String dni){
        return clientRepository.findByDni(dni);
    }

    public List<Client> listAll(){return clientRepository.findAll();}

    public void delete(Long id){
        clientRepository.deleteById(id);
    }
    public void delete(Client client){
        clientRepository.delete(client);
    }
}
