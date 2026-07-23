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
}
