package com.company.products;

import com.company.Product;

public class Cheese extends Product {
    public Cheese(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 40;
    }
}
