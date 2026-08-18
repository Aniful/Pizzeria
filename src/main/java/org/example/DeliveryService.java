package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryService {
    private CourierRepository courierRepository;
    private  OrderService orderService;

    public DeliveryService(CourierRepository courierRepository, OrderService orderService){
        this.courierRepository = courierRepository;
        this.orderService = orderService;
    }

    public List<Courier> getAvailableCouriers() {
        return courierRepository.getAvailableCouriers();
    }

    public void assignCourierToOrder(Long orderId, Long courierId) {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Курьер не найден"));

        if (courier.isAvailable() == false) {
            //исключение
        }

        if (order.getStatus() != Order.Status.READY) {
            //исключение
        }

        order.setCourierId(courierId);
        order.setStatus(Order.Status.DELIVERING);
        courier.setAvailable(false);

        orderService.updateOrder(order);
        courierRepository.save(courier);
    }

    public List<Courier> findAllCouriers() {
        return courierRepository.findAll();
    }

    public void updateCourier(Courier courier) {
        courierRepository.save(courier);
    }
}

