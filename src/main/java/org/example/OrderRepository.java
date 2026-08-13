package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable( orders.get(id) );
    }

    public List<Order> getOrdersByStatusForKitchen() {
        return orders.values().stream()
                .filter(order -> order.getStatus() == Order.Status.CONFIRMED || order.getStatus() == Order.Status.COOKING)
                .toList();
    }
}
