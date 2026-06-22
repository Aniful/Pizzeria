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
        COOKING,
        DELIVERING,
        COMPLETED,
        CANCELLED;

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

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCounterID(Long counterID) {
        this.courierID = counterID;
    }
}

