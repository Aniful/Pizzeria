package org.example;

import javax.xml.crypto.Data;
import java.util.List;

public class Order {
    private Long id;
    private Data data;
    private Long clientId;
    private List items;
    private Address address;
    private  Status status;

    private Long courierID;
    private static Long counterID = 0l;

    public enum Status {
        NEW,
        CONFIRMED,
        COOKING,
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

    public Order(Long clientId, Address address, List items) {
        id = ++counterID;
        this.clientId = clientId;
        this.address = address;
        this.items = items;

        status = Status.NEW;
    }

    public String getAddress() { return address.getFullAddress(); }
    public Status getStatus() { return status; }
    public Long getCourierID() { return courierID; }
    public Long getId() { return id; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCounterID(Long counterID) {
        this.courierID = counterID;
    }
}

