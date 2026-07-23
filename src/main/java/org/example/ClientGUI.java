package org.example;

import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {
    private ClientService clientService;
    private OrderService orderService;
    private Scanner scanner;
    private Client currentClient;

    public ClientGUI(ClientService clientService, OrderService orderService){
        this.clientService = clientService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);

    }

    public void Start() {
        registerOrLogin();

        while (true) {
            System.out.println("_____________________________________________________________");
            System.out.println("Добро пожаловать!");
            System.out.println("1. Сделать заказ");
            System.out.println("2. Посмотреть меню");
            System.out.println("0. Выйти");
            System.out.println("_____________________________________________________________");

             switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Создание заказа");
                    break;
                 case 2:
                     break;
                case 0:
                    return;
            };

        }
    }

    private void registerOrLogin() {
        System.out.println("_____________________________________________________________");
        System.out.println("Вход или регистрация");
        System.out.println("Введите номер телефона без специальных символов:");
        System.out.println("_____________________________________________________________");
        String numberPhone = scanner.next();

        Optional<Client> client = clientService.findByPhone(numberPhone);
        if (client.isEmpty()) {
            registerClient(numberPhone);
        } else {
            Client foundClient = client.get();
            System.out.println("Добро пожаловать, " + foundClient.getName());
        }
    }

    public void registerClient(String numberPhone) {
        System.out.println("_____________________________________________________________");
        System.out.println("Вы наш новый клиент! Подскажите как к вам обращаться:");
        System.out.println("_____________________________________________________________");

        String name = scanner.next();
        currentClient = clientService.registerClient(name, numberPhone);

        System.out.println("_____________________________________________________________");
        System.out.println("Регистрация прошла успешно!");
        System.out.println("_____________________________________________________________");

    }
}
