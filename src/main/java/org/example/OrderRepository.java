package org.example;

import java.util.HashMap;

public class OrderRepository {
    private HashMap<Long, Order> orders = new HashMap();
    private Long courierID = 0l;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(++courierID);
        }
        orders.put(order.getId(), order);
        return order;
    }
}
