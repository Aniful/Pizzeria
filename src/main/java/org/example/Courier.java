package org.example;

import java.util.ArrayList;
import java.util.List;

public class Courier {
    private Long id;
    private String name;
    private String numberPhone;
    private List<Long> deliveryHistory = new ArrayList<>();
    private boolean isAvailable;

    public Courier(String name, String numberPhone) {
        this.name = name;
        this.numberPhone = numberPhone;
        isAvailable = true;

    }

    public String getName() { return name; }
    public String getNumberPhone() { return numberPhone; }
    public boolean isAvailable() { return isAvailable; }
    public Long getId() { return id; }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addDeliveredOrder(Long orderId) {
        deliveryHistory.add(orderId);
    }
}
