package com.example.app;

import com.example.data.Clothing;
import com.example.data.Electronics;
import com.example.data.Product;
import com.example.processing.OrderProcessingException;
import com.example.processing.OrderProcessor;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        List<OrderProcessor<? extends Product>> orders = new ArrayList<>();

        // 1. Створення об'єктів за допомогою Lombok Builder та JavaFaker
        System.out.println("--- Generating Orders ---");

        for (int i = 0; i < 3; i++) {
            Electronics electronics = Electronics.builder()
                    .id(faker.idNumber().valid())
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .brand(faker.company().name())
                    .warrantyMonths(24)
                    .build();
            orders.add(new OrderProcessor<>(electronics));
        }

        for (int i = 0; i < 3; i++) {
            Clothing clothing = Clothing.builder()
                    .id(faker.idNumber().valid())
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .size("L")
                    .material(faker.commerce().material())
                    .build();
            orders.add(new OrderProcessor<>(clothing));
        }

        // 2. Багатопотокова обробка з використанням ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("\n--- Processing Orders in Threads ---");

        // Використання лямбда-виразу для ітерації та передачі задач у потік [cite: 13, 14]
        orders.forEach(processor -> {
            executor.submit(() -> {
                try {
                    // Логіка запуску процесора
                    processor.processOrder();
                    System.out.println("Completed by: " + Thread.currentThread().getName());
                } catch (OrderProcessingException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        });

        executor.shutdown();

        // 3. Приклад використання Method Reference (посилання на метод)
        // Створимо окремий список для демонстрації Method Reference (синхронно)
        System.out.println("\n--- Demo: Method Reference Output ---");
        orders.stream()
                .map(OrderProcessor::getProduct) // Method Reference
                .map(Product::getName)           // Method Reference
                .forEach(System.out::println);   // Method Reference
    }
}