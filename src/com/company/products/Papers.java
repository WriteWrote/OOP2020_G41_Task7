package com.company.products;

import com.company.Product;
public class Papers extends Product {
    public Papers(String name, int weight, int price, int quantity) {
        super(name, weight, price, quantity);
        expirationDays = 365;
    }
}
