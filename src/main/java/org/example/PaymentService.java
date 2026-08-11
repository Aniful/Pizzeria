package org.example;

import java.math.BigDecimal;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) { this.paymentRepository = paymentRepository; }

    public Payment payForOrder(Long orderId, BigDecimal amount, Payment.PaymentMethod method) {
        Payment payment = new Payment(orderId, amount, method);
        return paymentRepository.save(payment);
    }
}
