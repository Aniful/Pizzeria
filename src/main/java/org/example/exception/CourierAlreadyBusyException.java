package org.example.exception;

public class CourierAlreadyBusyException extends RuntimeException{
    public CourierAlreadyBusyException(String name) {
        super(String.format("Курьеру %s уже назначен другой заказа", name));
    }
}
