package org.example;

import java.util.Objects;

public class Address {
    private Long   id;
    private String city;
    private String street;
    private String home;
    private String apartment;

    public Address(String city, String street, String home, String apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Address address = (Address) obj;
//    }

    public Long   getId()       { return id; }
    public String getCity()     { return city; }
    public String getStreet()   { return street; }
    public String getHome()     { return home; }
    public String getApartment() { return apartment; }
    public String getFullAddress() {
        return String.format("%s, ул. %s, д. %s кв. %s", city, street, home, apartment);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
