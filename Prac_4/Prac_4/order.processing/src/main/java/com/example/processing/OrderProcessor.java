package com.example.processing;

import com.example.data.Product;

// Використання Generics з обмеженням
public class OrderProcessor<T extends Product> {
    private T product;

    public OrderProcessor(T product) {
        this.product = product;
    }

    // Метод для обробки замовлення
    public void processOrder() throws OrderProcessingException {
        if (product.getPrice() <= 0) {
            // Обробка виключень
            throw new OrderProcessingException("Price must be greater than 0 for product: " + product.getName());
        }

        System.out.printf("Processing order for [%s]: %s (Price: $%.2f)%n",
                product.getClass().getSimpleName(),
                product.getName(),
                product.getPrice());

        // Симуляція важкої роботи
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public T getProduct() {
        return product;
    }
}