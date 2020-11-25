package com.company;

import com.company.products.ProductType;
import com.company.utils.ProductUtils;
import com.company.utils.ServiceUtils;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private double money;
    private Map<ProductType, Integer> scaleOfDesires;

    public Customer(double money) {
        this.money = money;
        scaleOfDesires = ServiceUtils.generateSimpleDesires();
    }

    public double getMoney() {
        return money;
    }

    public Map<ProductType, Integer> getScaleOfDesires() {
        return scaleOfDesires;
    }
}
