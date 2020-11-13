package com.company;

import com.company.products.ProductType;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private double money;
    protected Map<ProductType, Integer> scaleOfDesires;

    public Customer(double money) {
        this.money = money;
        scaleOfDesires = this.generateSimpleDesires();
    }

    public double getMoney() {
        return money;
    }

    public Map<ProductType, Integer> getScaleOfDesires() {
        return scaleOfDesires;
    }


    private Map<ProductType, Integer> generateSimpleDesires() {
        HashMap<ProductType, Integer> scaleOfDesires = new HashMap<>();
        for (int i = 0; i < Math.random() * ProductType.getSize(); i++) {
            scaleOfDesires.put(ProductType.getRandomName(), (int)(Math.random()*9+1));
        }
        return scaleOfDesires;
    }
}
