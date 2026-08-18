package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CourierGUI {
    private DeliveryService deliveryService;
    private OrderService orderService;
    private Scanner scanner;
    private Courier currentCourier;

    public CourierGUI(DeliveryService deliveryService, OrderService orderService) {
        this.deliveryService = deliveryService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        login();

        while (true) {
            System.out.println("Меню. Текущий курьер: " + currentCourier.getName());
            System.out.println("1. Показать мои заказы");
            System.out.println("2. Завершить доставку");
            System.out.println("0. Выйти");
        }
    }

    private void login() {
        while (true) {
            Optional<Courier> courier = selectCourier();

            if (courier.isPresent()) {
                currentCourier = courier.get();
            } else {
                System.out.println("Курьер не выбран, попробуйте еще раз");
                continue;
            }

            if (currentCourier.hasPassword()) {
                if (checkPassword()) {
                    System.out.println("Добро пожаловать, " + currentCourier.getName());
                    return;
                } else {
                    System.out.println("Попробуйте еще раз");
                }

            } else {
                setupNewPassword(currentCourier);
                System.out.println("Добро пожаловать, " + currentCourier.getName());
                return;
            }
        }
    }

    private Optional<Courier> selectCourier() {
        List<Courier> couriers = deliveryService.findAllCouriers();
        if (couriers.isEmpty()) {
            System.out.println("В программе нет зарегистрированных курьеров, обратитесь к администратору");
            return Optional.empty();
        }

        System.out.println("_____________________________________________________________");
        System.out.println("Выберите себя из списка:");
        for (int i = 0; i < couriers.size(); i++) {
            System.out.println((i + 1) + ". " + couriers.get(i).getName());
        }
        System.out.println("_____________________________________________________________");

        int userChoice = scanner.nextInt();
        scanner.nextLine();

        if (userChoice > 0 && userChoice <= couriers.size()) {
            return Optional.of(couriers.get(userChoice - 1));
        } else {
            System.out.println("Введен неверный номер");
            return Optional.empty();
        }
    }

    private boolean checkPassword() {
        System.out.println("_____________________________________________________________");
        System.out.println("Введите пароль: ");
        String userPassword = scanner.nextLine();
        if (currentCourier.checkPassword(userPassword)) {
            System.out.println("Вход успешно выполнен");
            System.out.println("_____________________________________________________________");
            return true;
        } else {
            System.out.println("Неверный пароль");
            System.out.println("_____________________________________________________________");
            return false;
        }
    }

    private void setupNewPassword(Courier courier) {
        System.out.println("_____________________________________________________________");
        System.out.println("Установите пароль, вы будете вводить его при следующем входе: ");
        String userPassword = scanner.nextLine();
        courier.setPassword(userPassword);
        deliveryService.updateCourier(courier);
        System.out.println("Установлен новый пароль");
        System.out.println("_____________________________________________________________");
    }
}
