package org.example;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) { return orderRepository.findById(id); }

    public Order makeOrder(Long clientId, Address address, List<Pizza> items) {
        Order order = new Order(clientId, address, items);
        orderRepository.save(order);
        return order;
    }

    public List<Order> getOrdersByStatus(Predicate<Order> p) {
        return orderRepository.getOrdersByStatus(p);
    }

    public  List<Order> getOrdersForCourier(Long courierId) {
        return orderRepository.getOrdersForCourier(courierId);
    }

    public List<Order> getOrdersById(List<Long> ordersId) {
        return orderRepository.getOrdersById(ordersId);
    }

    public  List<Order> getOrdersByClient(Long clientId) {
        return orderRepository.getOrdersByClient(clientId);
    }
}
