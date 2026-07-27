package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class AdminGUI {
    private PizzaService pizzaService;
    private Scanner scanner;

    public AdminGUI(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить пиццу");
            System.out.println("0. Выйти");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    addPizza();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введенного значения не предусмотрено");
                    break;
            }
        }
    }

    //    public Pizza(String name, int diametr, int weight, BigDecimal currentPrice, String ingredients) {
    public void addPizza() {
        System.out.println("_____________________________________________________________");
        System.out.println("Введите название пиццы, которую хотите добавить: ");
        String namePizza = scanner.nextLine();
        System.out.println("Укажите диаметр:");
        int diametr = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите вес(в краммах):");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Цена пиццы:");
        BigDecimal currentPrice = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Опишите ингредиенты через запятую:");
        String ingredients = scanner.nextLine();

        Pizza currentPizza = pizzaService.addPizza(namePizza, diametr, weight, currentPrice, ingredients);
        System.out.print("Добавлена новая позиция: " + currentPizza.getDescription());
        System.out.println("Состав: " + currentPizza.getIngredients());
        System.out.println("_____________________________________________________________");
    }
}
