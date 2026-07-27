package org.example;

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

    public Client registerClient(String name, String numberPhone) {
        Client client = new Client(name, numberPhone);
        return clientRepository.save(client);
    }

    public Address createAddress(String city, String street, String home, String apartment) {
        Address address = new Address(city, street,home, apartment);

    }

    public void addAddressToClient(Long clientId, Address address) {
        Client client = clientRepository.findById(clientId);
        client.addAddress(address);
        clientRepository.save(client);
    }
}
