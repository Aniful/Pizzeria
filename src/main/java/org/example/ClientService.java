package org.example;

import org.example.exception.ClientNotFoundException;

import java.util.Optional;
import java.util.Scanner;

public class ClientService {

    private ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> findByPhone(String numberPhone) {
        return clientRepository.findByPhone(numberPhone);
    }
    public Optional<Client> findById(Long id) { return clientRepository.findById(id); }

    public Client registerClient(String name, String numberPhone) {
        Client client = new Client(name, numberPhone);
        return clientRepository.save(client);
    }

    public Address createAddress(String city, String street, String home, String apartment) {
        return new Address(city, street,home, apartment);
    }

    public void addAddressToClient(Long clientId, Address address) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        client.addAddress(address);
        clientRepository.save(client);
    }
}
