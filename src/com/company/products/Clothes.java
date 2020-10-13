package com.company.products;

import com.company.Product;

public class Clothes extends Product {
    public Clothes(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 3650;
    }
}
