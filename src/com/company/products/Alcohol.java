package com.company.products;

import com.company.Product;

public class Alcohol extends Product {
    public Alcohol(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 730;
    }
}
