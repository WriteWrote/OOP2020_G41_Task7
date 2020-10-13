package com.company.products;

import com.company.Product;

public class Toiletry extends Product {
    public Toiletry(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 730;
    }
}
