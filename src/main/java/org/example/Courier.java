package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Courier {
    private Long id;
    private String name;
    private String numberPhone;
    private List<Long> deliveryHistory = new ArrayList<>();
    private boolean isAvailable;
    private String password = null;

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

    public boolean hasPassword() {
        return password != null && !password.isEmpty();
    }

    public void setPassword(String password) {
        if (this.password != null) {
            // исключение
        }

        if (password == null || password.isEmpty()) {
            //исключение
        }

        this.password = password;
    }

    public boolean checkPassword(String input) {
        return Objects.equals(password, input);
    }

    public void addDeliveredOrder(Long orderId) {
        deliveryHistory.add(orderId);
    }
}
