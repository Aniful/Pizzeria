package org.example;

import java.util.Scanner;

public class ClientGUI {
    private ClientService clientService;
    private OrderService orderService;
    private Scanner scanner;
    private Client currentСlient;

    public ClientGUI(ClientService clientService, OrderService orderService){
        this.clientService = clientService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);

    }

    public void Start() {
        clientService.registerOrLogin();

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

}
