package com.company.products;

import com.company.Product;

public class Vegetables extends Product {
    public Vegetables(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 30;
    }
}
