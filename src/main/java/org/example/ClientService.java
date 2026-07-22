package org.example;

import java.util.Scanner;

public class ClientService {

    private ClientRepository clientRepository;
    private Scanner scanner = new Scanner(System.in);

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void registerOrLogin() {
        System.out.println("_____________________________________________________________");
        System.out.println("Вход или регистрация");
        System.out.println("Введите номер телефона без специальных символов:");
        System.out.println("_____________________________________________________________");
        String numberPhone = scanner.next();

        clientRepository.findByPhone(numberPhone);

    }
}
