package org.example;

import java.math.BigDecimal;

public class Pizza {
    private Long id;
    private String name;
    private int diametr;
    private BigDecimal currentPrice;
    private int weigth;
    private String ingredients;


    public Pizza(String name, int diametr, int weigth, BigDecimal currentPrice) {
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

    @Override
    public String toString() {
        return id + " " + name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public String getIngredients() { return ingredients; }

    public String getDescription() {
        return String.format("Пицца %s %dсм, %dг. Цена: %.2fр", name, diametr, weigth, currentPrice);
    }

    public void setName(String name) {
        if (name != "") {
            this.name = name;
        }
    }

    public  void setId(Long id) {
        this.id = id;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {

        this.currentPrice = currentPrice;
    }
}
