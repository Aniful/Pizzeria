package org.example;

import java.util.List;

public class Courier {
    private Long id;
    private String name;
    private String numberPhone;
    private List deliveryHistory;
    private boolean isAvailable;

    private static Long counterID = 0l;

    public Courier(String name, String numberPhone) {
        this.id = ++counterID;
        this.name = name;
        this.numberPhone = numberPhone;
    }

    public String getName() { return name; }
    public String getNumberPhone() { return numberPhone; }

}
