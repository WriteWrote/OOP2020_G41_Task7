package com.company.products;

import com.company.Product;

public class Seafood extends Product {
    public Seafood(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 60;
    }
}
