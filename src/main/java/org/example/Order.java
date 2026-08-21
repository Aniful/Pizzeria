package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private Long id;
    private LocalDateTime createdAt;
    private Long clientId;
    private Long courierId;
    private List<Pizza> items;
    private Address address;
    private Status status;
    private BigDecimal totalPrice;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public enum Status {
        NEW,
        CONFIRMED,
        COOKING,
        READY,
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
        this.createdAt = LocalDateTime.now();
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getAddress() { return address.getFullAddress(); }
    public Status getStatus() { return status; }
    public Long getId() { return id; }
    public Long getCourierId() { return courierId; }

    public String getDescription() {
        return String.format("Заказ №%d, будет доставлен по адресу: %s", id, address.getFullAddress());
    }

    public String getOrderSummary() {
        return  String.format("Заказ №%d от %s - %s;   %s",
                id,
                createdAt.format(DATE_TIME_FORMATTER),
                status,
                items.stream()
                        .map(Pizza::getName)
                        .collect(Collectors.joining(", ")));
    }

    public void setStatus(Status status) { this.status = status; }
    public void setId(Long id) { this.id = id; }
    public void setCourierId(Long courierId) { this.courierId = courierId;}
}

