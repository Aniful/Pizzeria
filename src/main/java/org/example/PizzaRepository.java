package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PizzaRepository {
    private Map<Long, Pizza> pizzas = new HashMap<>();
    private Long counterID = 0l;

    public List<Pizza> getMenu() {
        return  pizzas.values().stream().collect(Collectors.toList());

    }

    public Pizza save(Pizza pizza) {
        if (pizza.getId() == null) {
            pizza.setId(++counterID);
        }
        pizzas.put(pizza.getId(), pizza);
        return pizza;
    }

}
