package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    private Long id;
    private String name;
    private String numberPhone;
    private Date biethDate;
    private List<Address> addressHistory = new ArrayList<>();

    private static Long counterID = 0l;

    public Client(String name, String numberPhone) {
        id = ++counterID;
        this.name = name;
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setBiethDate(Date biethDate) {
        this.biethDate = biethDate;
    }

    public void addAddress(Address address) {
        if (addressHistory.contains(address) == false) {
            addressHistory.add(address);
        }
    }
}
