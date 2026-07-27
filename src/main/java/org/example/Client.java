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


    public Client(String name, String numberPhone) {
        this.name = name;
        this.numberPhone = numberPhone;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getNumberPhone() { return numberPhone; }
    public List<Address> getAddressHistory() { return addressHistory; }

    void setId(Long id) {
        this.id = id;
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
