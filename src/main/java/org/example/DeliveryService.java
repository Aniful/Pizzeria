package org.example;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    private List<Courier> couriers = new ArrayList<>();
    private CourierRepository courierRepository;

    public DeliveryService(CourierRepository courierRepository){
        this.courierRepository = courierRepository;
    }

    public void addCourier(String name, String numberPhone) {
        Courier courier = new Courier(name, numberPhone);
        couriers.add(courier);
    }

    public Courier findCourierByID(Long id) {
        for (Courier courier : couriers) {
            if (courier.getId().equals(id)) return courier;
        }
        return null;
    }

    public void assignCourier(Order order){
        Courier availableCourier = findAvailableCourier();

        if (availableCourier != null) {
//            order.setCounterID(availableCourier.getId());
//            availableCourier.setAvailable(false);
        } else {
            System.out.println("Свободные курьеры отсутствуют");
        }

    }

    private Courier findAvailableCourier() {
        Courier availableCourier = null;

        for (Courier courier : couriers) {
            if (courier.getIsAvailable()) {
                availableCourier = courier;
            }
        }
        return availableCourier;
    }

    public void startDelivery(Order order) {
        if (order.getStatus().isCOOKING()) {
            order.setStatus(Order.Status.DELIVERING);
        }
    }

    public void completeDelivery(Order order) {
        if (order.getStatus().isDELIVERING()) {
            order.setStatus(Order.Status.COMPLETED);
//            Courier courier = findCourierByID( order.getCourierID() );
//            courier.setAvailable(true);
//            System.out.printf("Заказ №%s доставлен!\n", order.getId());
        }
    }
}
