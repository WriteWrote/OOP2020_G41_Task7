package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Customer {
    private double money;
    protected Map<ExampleProduct, Integer> scaleOfDesires;

    public Customer(double money) {
        this.money = money;
        scaleOfDesires = this.generateSimpleDesires();
    }

    public double getMoney() {
        return money;
    }

    public Map<ExampleProduct, Integer> getScaleOfDesires() {
        return scaleOfDesires;
    }


    private Map<ExampleProduct, Integer> generateSimpleDesires() {
        HashMap<ExampleProduct, Integer> scaleOfDesires = new HashMap<>();
        for (int i = 0; i < Math.random() * ExampleProduct.getSize(); i++) {
            scaleOfDesires.put(ExampleProduct.getRandomName(), (int)(Math.random()*5+1));
        }
        return scaleOfDesires;
    }
}
