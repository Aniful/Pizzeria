package org.example.exception;

import org.example.Order;

public class OrderNotInDeliveryException extends RuntimeException{
    public OrderNotInDeliveryException(Long orderId, Order.Status orderStatus) {
        super("Заказ №" + orderId + " не передан в доставку, его статус - " + orderStatus);
    }
}
