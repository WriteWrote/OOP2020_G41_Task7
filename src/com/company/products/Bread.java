package com.company.products;

import com.company.Product;

public class Bread extends Product {
    public Bread(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 4;
    }
}
