package org.example;

import java.math.BigDecimal;

public class Pizza {
    private Long id;
    private String name;
    private int diametr;
    private BigDecimal currentPrice;
    private int weigth;
    private String ingredients;

    public String getName() {
        return name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void getDescription() {
        System.out.printf("Пицца %s см%d, цена: %fр", name, diametr, currentPrice);
    }
}
