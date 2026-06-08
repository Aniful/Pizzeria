package org.example;

import javax.xml.crypto.Data;
import  java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Data data;
    private Client client;
    private List structure;
    private Courier courier;
    private Address address;
    private  Status status;

    private static Long counterID = 0l;

    public Order(Client client, Address address, List structure) {
        id = ++counterID;
        this.client = client;
        this.address = address;
        this.structure = structure;
        client.addAddress(address);
    }

    public String getAddress() {
        return address.getFullAddress();
    }

    public enum Status {
        NEW,
        COOKING,
        DELIVERING,
        COMPLETED
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) {
        this.status = status;
    }
}

