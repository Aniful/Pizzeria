package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AdminGUI {
    private OrderService orderService;
    private DeliveryService deliveryService;
    private PizzaService pizzaService;
    private Scanner scanner;

    public AdminGUI(OrderService orderService, DeliveryService deliveryService, PizzaService pizzaService) {
        this.orderService = orderService;
        this.deliveryService = deliveryService;
        this.pizzaService = pizzaService;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Назначить курьера");
            System.out.println("2. Добавить пиццу");
            System.out.println("3. Добавить курьера");
            System.out.println("0. Выйти");
            System.out.println("_____________________________________________________________");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    selectOrderForCourier();
                    break;
                case 2:
                    addPizza();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введенного значения не предусмотрено");
                    break;
            }
        }
    }

    //    public Pizza(String name, int diametr, int weight, BigDecimal currentPrice, String ingredients) {
    public void addPizza() {
        System.out.println("_____________________________________________________________");
        System.out.println("Введите название пиццы, которую хотите добавить: ");
        String namePizza = scanner.nextLine();
        System.out.println("Укажите диаметр:");
        int diametr = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите вес(в краммах):");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Цена пиццы:");
        BigDecimal currentPrice = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Опишите ингредиенты через запятую:");
        String ingredients = scanner.nextLine();

        Pizza currentPizza = pizzaService.addPizza(namePizza, diametr, weight, currentPrice, ingredients);
        System.out.print("Добавлена новая позиция: " + currentPizza.getDescription());
        System.out.println("Состав: " + currentPizza.getIngredients());
        System.out.println("_____________________________________________________________");
    }

    private void selectOrderForCourier() {
        while (true) {
            List<Order> orders = displayOrders();

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 0) {
                return;
            }

            if (userChoice > 0 && userChoice <= orders.size()) {
                Order order = orders.get(userChoice-1);
                System.out.println("Выбран Заказ №" + order.getId());
                assignCourierToOrder(order);
            } else {
                System.out.println("Введен неверный номер");
            }
        }
    }

    private List<Order> displayOrders() {
        List<Order> orders = orderService.getOrdersByStatus(order -> order.getStatus() == Order.Status.READY);
        System.out.println("_____________________________________________________________");
        System.out.println("Заказы готовые к доставке:");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". " + order.getOrderSummary() + " | " + order.getAddress());
        }
        System.out.println("0. Выйти");
        System.out.println("_____________________________________________________________");

        return orders;
    }

    private void assignCourierToOrder(Order order) {
        List<Courier> couriers = displayAvailableCouriers();

        if (couriers.isEmpty() || couriers == null) { return;}

        int userChoice = scanner.nextInt();
        scanner.nextLine();

        if (userChoice == 0) { return;}

        if (userChoice > 0 && userChoice <= couriers.size()) {
            Courier courier = couriers.get(userChoice - 1);
            deliveryService.assignCourierToOrder(order.getId(), courier.getId());
            System.out.println("Заказ №" + order.getId() + " передан в доставку. Курьер: " + courier.getName());
        } else {
            System.out.println("Введен неверный номер");
        }
    }

    private List<Courier> displayAvailableCouriers() {
        System.out.println("_____________________________________________________________");
        List<Courier> availableCouriers = deliveryService.getAvailableCouriers();

        if (availableCouriers.isEmpty()) {
            System.out.println("На данный момент свободных курьеров нет, попробуйте позже");
        } else {
            System.out.println("Свободные курьеры:");
            for (int i = 0; i < availableCouriers.size(); i++) {
                System.out.println((i + 1) + ". " + availableCouriers.get(i).getName());
            }
            System.out.println("0. Вернуться к заказам");
        }

        return availableCouriers;
    }
}
