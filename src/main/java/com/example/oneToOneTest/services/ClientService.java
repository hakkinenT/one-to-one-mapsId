package com.example.oneToOneTest.services;

import com.example.oneToOneTest.dto.ClientDTO;
import com.example.oneToOneTest.entities.Address;
import com.example.oneToOneTest.entities.Client;
import com.example.oneToOneTest.repositories.AddressRepository;
import com.example.oneToOneTest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();

        copyDtoToEntity(dto, client);

        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setNumber(dto.getAddress().getNumber());
            address.setNeighborhood(dto.getAddress().getNeighborhood());
            address.setState(dto.getAddress().getState());
            address.setZipCode(dto.getAddress().getZipCode());
            address.setClient(client);

            client.setAddress(address);
        }
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = clientRepository.getReferenceById(id);


        if (entity.getAddress() != null) {
            Address address = new Address();
            address.setId(entity.getAddress().getId());
            copyDtoToReferencedEntities(dto, entity, address);
        } else {
            copyDtoToEntity(dto, entity);
        }


        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    private void copyDtoToReferencedEntities(ClientDTO dto, Client client, Address address) {
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        address.setStreet(dto.getAddress().getStreet());
        address.setNeighborhood(dto.getAddress().getNeighborhood());
        address.setCity(dto.getAddress().getCity());
        address.setNumber(dto.getAddress().getNumber());
        address.setState(dto.getAddress().getState());
        address.setZipCode(dto.getAddress().getZipCode());

        address.setClient(client);

        client.setAddress(address);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> dtos = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
        return dtos;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return new ClientDTO(client);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
