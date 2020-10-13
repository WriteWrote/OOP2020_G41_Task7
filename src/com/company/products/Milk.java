package com.company.products;

import com.company.Product;

public class Milk extends Product {
    public Milk(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 10;
    }
}
