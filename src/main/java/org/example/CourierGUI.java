package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourierGUI {
    private OrderService orderService;
    private Scanner scanner;

    public CourierGUI(OrderService orderService) {
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {



    }

    private void displayOrders(List<Order> orders) {

    }
}
