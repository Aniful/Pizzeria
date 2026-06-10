package org.example;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    private List<Courier> couriers = new ArrayList<>();

    public void addCourier(String name, String numberPhone) {
        Courier courier = new Courier(name, numberPhone);
        couriers.add(courier);
    }

    public void assignCourier(Order order){
        Courier availableCourier = findAvailableCourier();

        if (availableCourier != null) {
            order.setCounterID(availableCourier.getId());
            availableCourier.setAvailable(false);
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
}
