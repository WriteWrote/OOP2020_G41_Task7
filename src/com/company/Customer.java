package com.company;

import com.company.products.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class Customer {
    private double money;
    protected Map<Product, Integer> scaleOfDesires;

    public Customer(double money) {
        this.money = money;
        scaleOfDesires = this.generateSimpleDesires();
    }

    public double getMoney() {
        return money;
    }

    public Map<Product, Integer> getScaleOfDesires() {
        return scaleOfDesires;
    }


    private Map<Product, Integer> generateSimpleDesires() {
        HashMap<Product, Integer> scaleOfDesires = new HashMap<>();
        for (int i = 0; i < Math.random() * Product.getSize(); i++) {
            scaleOfDesires.put(Product.getRandomName(), (int)(Math.random()*5+1));
        }
        return scaleOfDesires;
    }
}
