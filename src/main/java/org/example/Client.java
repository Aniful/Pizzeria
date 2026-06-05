package org.example;

import java.time.LocalDate;
import java.util.List;

public class Client {
    private Long id;
    private String name;
    private String clientNumber;
    private LocalDate biethDate;
    private List addressHistory;

    public String getName() {
        return name;
    }

    public String getClientNumber() {
        return clientNumber;
    }
}
