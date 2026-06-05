package org.example;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Pizza margarita = new Pizza("Маргарита", 25, 400, new BigDecimal("450"));
        margarita.getDescription();
    }
}
