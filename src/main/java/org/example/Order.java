package org.example;

import javax.xml.crypto.Data;
import java.util.List;

public class Order {
    private Long id;
    private Data data;
    private Client client;
    private List items;
    private Long courierID;
    private Address address;
    private  Status status;

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

    public Order(Client client, Address address, List items) {
        id = ++counterID;
        this.client = client;
        this.address = address;
        this.items = items;
        client.addAddress(address);

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

