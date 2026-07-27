package org.example;

import java.math.BigDecimal;
import java.util.List;

public class PizzaService {
    private PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getMenu() {
        return pizzaRepository.getMenu();
    }

    public Pizza addPizza(String name, int diametr, int weigth, BigDecimal currentPrice, String ingredients) {
        Pizza pizza = new Pizza(name, diametr, weigth, currentPrice, ingredients);
        return pizzaRepository.save(pizza);
    }
}
