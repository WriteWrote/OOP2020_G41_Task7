package com.company.products;

import com.company.Product;

public class Fish extends Product {
    public Fish(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 90;
    }
}
