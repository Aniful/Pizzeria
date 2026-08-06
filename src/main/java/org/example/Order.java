package org.example;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Long id;
    private Data data;
    private Long clientId;
    private List<Pizza> items;
    private Address address;
    private Status status;
    private BigDecimal totalPrice;

    public enum Status {
        NEW,
        CONFIRMED,
        COOKING,
        DELIVERING,
        COMPLETED,
        CANCELLED;

        public  boolean isCONFIRMED() {
            return  this == CONFIRMED;
        }

        public  boolean isCOOKING() {
            return  this == COOKING;
        }

        public boolean isDELIVERING() {
            return this == DELIVERING;
        }
    }

    public Order(Long clientId, Address address, List<Pizza> items) {
        this.clientId = clientId;
        this.address = address;
        this.items = items;
        this.totalPrice = items.stream()
                .map(Pizza::getCurrentPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        status = Status.NEW;
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getAddress() { return address.getFullAddress(); }
    public Status getStatus() { return status; }
    public Long getId() { return id; }

    public void setStatus(Status status) { this.status = status; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() {
        return String.format("Заказ №%d, будет доставлен по адресу: %s", id, address.getFullAddress());
    }
}

