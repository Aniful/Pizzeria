package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Pizza margarita = new Pizza("Маргарита", 25, 400, new BigDecimal("450"));
        margarita.getDescription();

        Client andry = new Client("Andry", "89002008787");
        Address address1 = new Address("Пермь", "Ленина", "1", "7");
        List<Pizza> structureOrder1 = new ArrayList<>();
        structureOrder1.add(margarita);

        Order order1 = new Order(andry, address1, structureOrder1);
        Order order2 = new Order(andry, address1, structureOrder1);

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.addCourier("Kiril", "85673338987");
        deliveryService.assignCourier(order1);

        List<String> books = new ArrayList<>();
        books.add("Ведьмина служба доставки");
        books.add("Вторая жизнь Уве");
        books.add("Кармен");
        books.add("Раковый корпус");
        books.add("Раковый корпус");
        books.add("Любимчик эпохи");
        books.add("Любимчик эпохи");
        books.add("впмит");

        System.out.println(books.size());
        System.out.println(books.contains("Кармен"));
        System.out.println(books.toString());

        HashSet hashSet = new HashSet<>(books);
        System.out.println(hashSet.toString());
    }
}
