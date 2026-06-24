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
        List<Pizza> itemsOrder1 = new ArrayList<>();
        itemsOrder1.add(margarita);

        Order order1 = new Order(andry, address1, itemsOrder1);
        Order order2 = new Order(andry, address1, itemsOrder1);

        DeliveryService deliveryService = new DeliveryService();
        OrderService orderService = new OrderService();

        order1.setStatus(Order.Status.CONFIRMED);

        deliveryService.addCourier("Kiril", "85673338987");
        deliveryService.assignCourier(order1);

        orderService.sendCook(order1);
        deliveryService.startDelivery(order1);

        deliveryService.completeDelivery(order1);

    }
}
