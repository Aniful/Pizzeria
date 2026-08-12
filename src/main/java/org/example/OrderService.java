package org.example;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) { return orderRepository.findById(id); }

    public Order updateOrder(Order order) {
            return orderRepository.save(order);
    }

    public Order makeOrder(Long clientId, Address address, List<Pizza> items) {
        Order order = new Order(clientId, address, items);
        orderRepository.save(order);
        return order;
    }

    public void sendCook(Order order){
        if (order.getStatus().isCONFIRMED()) {
            order.setStatus(Order.Status.COOKING);
        }
    }
}
