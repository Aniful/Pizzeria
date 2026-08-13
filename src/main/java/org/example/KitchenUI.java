package org.example;

import java.util.List;
import java.util.Scanner;

public class KitchenUI {
    private Scanner scanner;
    private OrderService orderService;

    public KitchenUI(OrderService orderService) {
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            List<Order> orders = orderService.getOrdersByStatusForKitchen();
            System.out.println("_____________________________________________________________");
            System.out.println("Список заказов: ");
            displayOrders(orders);
            System.out.println("0. Выйти");
            System.out.println("Введите номер для изменения статуса:");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 0) {
                return;
            }

            if (userChoice > 0 && userChoice <= orders.size()) {
                Order order = orders.get(userChoice - 1);
                changeOrderStatus(order);
            } else {
                System.out.println("Введен неверный номер");
            }
        }
    }

    private void displayOrders(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". " + order.getOrderSummary());
        }
    }

    private void changeOrderStatus(Order order) {
        System.out.println("_____________________________________________________________");
        System.out.println("Смена статуса");
        System.out.printf("Выбран Заказ №%d. Текущий статус: %s\n", order.getId(), order.getStatus());
        System.out.println("1. Приступаем к готовке - COOKING");
        System.out.println("2. Заказ готов к доставке - READY");
        System.out.println("0. Вернуться к заказам");
        System.out.println("_____________________________________________________________");

        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case 1:
                order.setStatus(Order.Status.COOKING);
                System.out.println("Статус изменен на - COOKING");
                break;
            case 2:
                order.setStatus(Order.Status.READY);
                System.out.println("Статус изменен на - READY");
                break;
            case 0:
                return;
        }

        orderService.updateOrder(order);
    }
}
