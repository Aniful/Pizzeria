package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private Status status;
    private PaymentMethod method;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public enum Status {
        PENDING,
        SUCCESS,
        REFUNDED;
    }

    public enum PaymentMethod {
        CARD,
        CASH,
        SPB;
    }

    public Payment(Long orderId, BigDecimal amount, PaymentMethod method) {
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;

        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
        this.completedAt = null;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

}
