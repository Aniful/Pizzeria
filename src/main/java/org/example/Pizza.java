package org.example;

import java.math.BigDecimal;

public class Pizza {
    private Long id;
    private String name;
    private int diametr;
    private BigDecimal currentPrice;
    private int weigth;
    private String ingredients;

    private static Long counterID = 0l;

    public Pizza(String name, int diametr, int weigth, BigDecimal currentPrice) {
        this.id = ++counterID;
        this.name = name;
        this.diametr = diametr;
        this.weigth = weigth;
        this.currentPrice = currentPrice;

    }
    public Pizza(String name, int diametr, int weigth, BigDecimal currentPrice, String ingredients) {
        this.name = name;
        this.diametr = diametr;
        this.weigth = weigth;
        this.currentPrice = currentPrice;
        this.ingredients = ingredients;

    }

    public String getName() { return name; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public String getIngredients() { return ingredients; }

    public void getDescription() {
        System.out.printf("Пицца %s %dсм, %dг. Цена: %fр", name, diametr, weigth, currentPrice);
    }

    public void setName(String name) {
        if (name != "") {
            this.name = name;
        }
    }

    public void setCurrentPrice(BigDecimal currentPrice) {

        this.currentPrice = currentPrice;
    }
}
