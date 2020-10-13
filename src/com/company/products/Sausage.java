package com.company.products;

import com.company.Product;

public class Sausage extends Product {
    public Sausage(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 35;
    }
}
