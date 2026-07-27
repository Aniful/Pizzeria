package org.example;

import java.util.List;

public class OrderService {

    public void makeOrder(Long clientId, Address address, List items) {

    }

    public void sendCook(Order order){
        if (order.getStatus().isCONFIRMED()) {
            order.setStatus(Order.Status.COOKING);
        }
    }
}
