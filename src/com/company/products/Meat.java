package com.company.products;

import com.company.Product;

public class Meat extends Product {
    public Meat(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 30;
    }
}
