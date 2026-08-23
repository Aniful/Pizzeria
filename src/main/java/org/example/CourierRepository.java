package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CourierRepository {
    private HashMap<Long, Courier> couriers = new HashMap<>();
    private Long counterID = 0l;

    public Courier save(Courier courier) {
        if (courier.getId() == null) {
            courier.setId(++counterID);
        }
        couriers.put(courier.getId(), courier);
        return courier;
    }

    public List<Courier> getAvailableCouriers(){
        return couriers.values().stream()
                .filter(Courier::isAvailable)
                .toList();
    }

    public Optional<Courier> findById(Long id) {
        return Optional.ofNullable(couriers.get(id));
    }

    public Optional<Courier> findByPhone(String numberPhoneNewCourier) {
        return couriers.values().stream()
                .filter(courier -> courier.getNumberPhone().equals(numberPhoneNewCourier))
                .findFirst();
    }

    public  List<Courier> findAll() {
        return couriers.values().stream().toList();
    }
}
