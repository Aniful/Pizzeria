package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class PaymentService {
    private PaymentRepository paymentRepository;
    private OrderService orderService;

    public PaymentService(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public Payment payForOrder(Long orderId, BigDecimal amount, Payment.PaymentMethod method) {
        Payment payment = new Payment(orderId, amount, method);
        return paymentRepository.save(payment);
    }

    public void processPayment(Payment payment, Order order) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        payment.setStatus(Payment.Status.SUCCESS);
        payment.setCompletedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        order.setStatus(Order.Status.CONFIRMED);
        orderService.updateOrder(order);
    }

    public Optional<Payment> findById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
