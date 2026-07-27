package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

          Pizza margarita = new Pizza("Маргарита", 25, 400, new BigDecimal("450"), "Помидор, Сыр, Специи");
          Pizza hot = new Pizza("Острая", 30, 340, new BigDecimal("400"), "Лук, Перец, Чеснок");

//        System.out.println(margarita.getDescription());
//
//        Client andry = new Client("Andry", "89002008787");
//        Address address1 = new Address("Пермь", "Ленина", "1", "7");
//        List<Pizza> itemsOrder1 = new ArrayList<>();
//        itemsOrder1.add(margarita);
//
//        Order order1 = new Order(andry, address1, itemsOrder1);
//        Order order2 = new Order(andry, address1, itemsOrder1);
//
//        DeliveryService deliveryService = new DeliveryService();
//        OrderService orderService = new OrderService();
//
//        order1.setStatus(Order.Status.CONFIRMED);
//
//        deliveryService.addCourier("Kiril", "85673338987");
//        deliveryService.assignCourier(order1);
//
//        orderService.sendCook(order1);
//        deliveryService.startDelivery(order1);
//
//        deliveryService.completeDelivery(order1);
        OrderService orderService = new OrderService();
        DeliveryService deliveryService = new DeliveryService();

        ClientRepository clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);

        PizzaRepository pizzaRepository = new PizzaRepository();
        PizzaService pizzaService = new PizzaService(pizzaRepository);
        pizzaRepository.save(margarita);
        pizzaRepository.save(hot);

        while (true) {
            System.out.println("_____________________________________________________________");
            System.out.println("Выберите пользователя введя соотвествующий номер: ");
            System.out.println("1. Клиент");
            System.out.println("2. Повар");
            System.out.println("3. Доставщик");
            System.out.println("4. Администратор");
            System.out.println("_____________________________________________________________");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    ClientGUI clientGUI = new ClientGUI(clientService, orderService, pizzaService);
                    clientGUI.start();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    AdminGUI adminGUI = new AdminGUI(pizzaService);
                    adminGUI.start();
                    break;
                default:
                    System.out.println("Введенного значения не предусмотрено");
            }
        }

    }
}
