package org.example.exception;

import org.example.Order;

public class InvalidOrderStatusException extends RuntimeException{
    public InvalidOrderStatusException(Long orderId, Order.Status orderStatus, Order.Status expectedStatus) {
        super(String.format("Заказ №%d в статусе - %s, когда ожидается - %s" ,orderId , orderStatus, expectedStatus));
    }
}
