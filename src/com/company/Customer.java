package com.company;

import com.company.products.ProductType;
import com.company.utils.CustomerService;

import java.util.Map;

public class Customer {
    private double money;
    private Map<ProductType, Integer> scaleOfDesires;

    public Customer(double money) {
        CustomerService utils = new CustomerService();
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