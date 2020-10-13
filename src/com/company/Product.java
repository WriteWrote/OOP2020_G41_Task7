package com.company;

public abstract class Product {
    private String name;
    private int weight, price, quantity;
    protected int expirationDays;

    public Product(String name, int weight, int price, int quantity) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getExpirationDays() {
        return expirationDays;
    }
}
