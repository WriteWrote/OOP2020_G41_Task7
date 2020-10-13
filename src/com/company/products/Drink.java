package com.company.products;

import com.company.Product;

public class Drink extends Product {
    public Drink(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 21;
    }
}
