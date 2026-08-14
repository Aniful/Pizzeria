package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

          Pizza margarita = new Pizza("Маргарита", 25, 400, new BigDecimal("450"), "Помидор, Сыр, Специи");
          Pizza hot = new Pizza("Острая", 30, 340, new BigDecimal("400"), "Лук, Перец, Чеснок");

          Courier benTen = new Courier("Ben Ten", "71210001212");
          Courier bobTen = new Courier("Bob Ten", "74440001414");
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
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);
        CourierRepository courierRepository = new CourierRepository();
        DeliveryService deliveryService = new DeliveryService(courierRepository, orderService);

        ClientRepository clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);

        PizzaRepository pizzaRepository = new PizzaRepository();
        PizzaService pizzaService = new PizzaService(pizzaRepository);

        PaymentRepository paymentRepository = new PaymentRepository();
        PaymentService paymentService = new PaymentService(paymentRepository, orderService);
        pizzaRepository.save(margarita);
        pizzaRepository.save(hot);
        courierRepository.save(benTen);
        courierRepository.save(bobTen);

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
                    ClientGUI clientGUI = new ClientGUI(clientService, orderService, pizzaService, paymentService);
                    clientGUI.start();
                    break;
                case 2:
                    KitchenGUI kitchenGUI = new KitchenGUI(orderService);
                    kitchenGUI.start();
                    break;
                case 3:
                    CourierGUI courierGUI = new CourierGUI(orderService);
                    courierGUI.start();
                    break;
                case 4:
                    AdminGUI adminGUI = new AdminGUI(orderService, deliveryService, pizzaService);
                    adminGUI.start();
                    break;
                default:
                    System.out.println("Введенного значения не предусмотрено");
            }
        }

    }
}
