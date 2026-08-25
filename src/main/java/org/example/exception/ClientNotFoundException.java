package org.example.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long clientId) {
        super("Клиент с ID" + clientId + " не найден");
    }
}
