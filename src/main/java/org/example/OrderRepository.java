package org.example;

import java.util.*;
import java.util.function.Predicate;

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

    public List<Order> getOrdersByStatus(Predicate<Order> p) {
        return orders.values().stream()
                .filter(p)
                .toList();
    }

    public List<Order> getOrdersForCourier(Long courierId) {
        return orders.values().stream()
                .filter(order -> order.getStatus() == Order.Status.DELIVERING)
                .filter(order -> order.getCourierId().equals(courierId))
                .toList();
    }

    public List<Order> getOrdersById(List<Long> ordersId) {
        if (ordersId.isEmpty() || ordersId == null) {
            return Collections.emptyList();
        }

        return orders.values().stream()
                .filter(order -> ordersId.contains(order.getId()))
                .toList();
    }

    public List<Order> getOrdersByClient(Long clientId) {
        return orders.values().stream()
                .filter(order -> order.getClientId().equals(clientId))
                .sorted(Comparator.comparing(Order::getCreatedAt))
                .toList().reversed();
    }
}
