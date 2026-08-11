package org.example;

import java.util.HashMap;

public class PaymentRepository {
    HashMap<Long, Payment> payments = new HashMap<>();
    private Long counterID = 0l;

    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(++counterID);
        }
        payments.put(payment.getId(), payment);
        return payment;
    }
}
