package com.company.products;

import com.company.Product;

public class Sweeties extends Product {
    public Sweeties(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 14;
    }
}
