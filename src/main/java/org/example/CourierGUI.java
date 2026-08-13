package org.example;

import java.util.ArrayList;
import java.util.List;

public class CourierGUI {
    private OrderService orderService;

    public CourierGUI(OrderService orderService) {
        this.orderService = orderService;
    }

    public void start() {
        List<Order> orders = orderService.getOrdersByStatus(order -> order.getStatus() == Order.Status.READY);
        System.out.println("Зкзакы готовый к доставке:");
        displayOrders(orders);

    }

    private void displayOrders(List<Order> orders) {

    }
}
