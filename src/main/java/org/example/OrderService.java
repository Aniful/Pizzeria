package org.example;

public class OrderService {

    public void sendCook(Order order){
        if (order.getStatus().isCONFIRMED()) {
            order.setStatus(Order.Status.COOKING);
        }
    }
}
