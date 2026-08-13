package org.example;

import java.util.HashMap;

public class CourierRepository {
    private HashMap<Long, Courier> couriers = new HashMap<>();
    private Long courierID = 0l;

    public Courier save(Courier courier) {
        if (courier.getId() == null) {
            courier.setId(++courierID);
        }
        couriers.put(courier.getId(), courier);
        return courier;
    }
}
