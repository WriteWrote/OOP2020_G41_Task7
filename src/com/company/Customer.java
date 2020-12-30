package com.company;

import com.company.products.ProductType;
import com.company.utils.CustomerUtils;

import java.util.Map;

public class Customer {
    private double money;
    private Map<ProductType, Integer> scaleOfDesires;

    public Customer(double money) {
        CustomerUtils utils = new CustomerUtils();
        this.money = money;
        scaleOfDesires = utils.generateSimpleDesires();
    }

    public double getMoney() {
        return money;
    }

    public Map<ProductType, Integer> getScaleOfDesires() {
        return scaleOfDesires;
    }
}