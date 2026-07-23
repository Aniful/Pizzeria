package org.example;

import java.util.HashMap;
import java.util.Optional;

public class ClientRepository {
    private HashMap<Long, Client> clients = new HashMap<>();
    private static Long counterID = 0l;

    public Optional<Client> findByPhone(String numberPhone) {
            Optional<Client> result = clients.values().stream()
                    .filter(client -> numberPhone.equals(client.getNumberPhone()) )
                    .findFirst();

            return result;
    }

    public Client save(Client client) {
        client.setId(++counterID);
        clients.put(client.getId(), client);
        return client;
    }
}
