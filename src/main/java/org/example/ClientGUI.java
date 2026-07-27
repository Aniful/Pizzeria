package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {
    private ClientService clientService;
    private OrderService orderService;
    private PizzaService pizzaService;
    private Scanner scanner;
    private Client currentClient;

    public ClientGUI(ClientService clientService, OrderService orderService, PizzaService pizzaService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.pizzaService = pizzaService;
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        registerOrLogin();

        while (true) {
            System.out.println("_____________________________________________________________");
            System.out.println("Здесть вы можете:");
            System.out.println("1. Сделать заказ");
            System.out.println("2. Посмотреть состав пиццы");
            System.out.println("0. Выйти");
            System.out.println("_____________________________________________________________");

            int userChoiсe = scanner.nextInt();
            scanner.nextLine();
            switch (userChoiсe) {
                case 1:
                    makeOrder();
                    break;
                case 2:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введенного значения не предусмотрено");
            }

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
            currentClient = client.get();
            System.out.println("Добро пожаловать, " + currentClient.getName());
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
    }

    public void makeOrder() {
        System.out.println("_____________________________________________________________");
        System.out.println("МЕНЮ ПИЦЦЕРИИ.");
        List<Pizza> menu = pizzaService.getMenu();
        for (Pizza position : menu) {
            System.out.println(position.getId() + " " + position.getDescription());
        }
        System.out.print("Введите номера пицц через запятую (пробелы по желанию):");
        String userChoice = scanner.nextLine();
        System.out.println("_____________________________________________________________");

        //парсинг
        userChoice.replaceAll("\\s+", "");
        List<String> items = List.of( userChoice.split(",") );

        List<Address> addressHistory = currentClient.getAddressHistory();
        if (addressHistory.isEmpty()) {
            System.out.println("У вас еще нет ни одного адреса доставки, давайте это исправим");
            addAddress();
        }
        System.out.println("Укажите адрес доставки: ");
        String address = scanner.nextLine();

//        orderService.makeOrder(currentClient.getId(), address, items);
        // String city, String street, String home, String apartment)
    }

    public void addAddress() {
        System.out.println("Введите город:");
        String city = scanner.nextLine();
        System.out.println("Введите улицу:");
        String street = scanner.nextLine();
        System.out.println("Введите номер дома:");
        String home = scanner.nextLine();
        System.out.println("Введите номер квартиры:");
        String apartment = scanner.nextLine();

        Address address = clientService.createAddress(city, street, home, apartment);
        clientService.addAddressToClient(currentClient.getId(), address);
    }
}
