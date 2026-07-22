package org.example;

import java.util.HashMap;
import java.util.Optional;

public class ClientRepository {
    private HashMap<Long, Client> clients = new HashMap<>();

    public Optional<Client> findByPhone(String numberPhone) {
            Optional<Client> result = clients.values().stream()
                    .filter(client -> numberPhone.equals(client.getNumberPhone()) )
                    .findFirst();

            return result;
    }

    //public save()
}
