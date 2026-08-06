package org.example;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
