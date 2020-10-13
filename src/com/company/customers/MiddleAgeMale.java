package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

import java.time.temporal.ChronoUnit;

public class MiddleAgeMale extends Customer {
    public MiddleAgeMale(double money) {
        super(money);
        generatePersonalDesires();
    }

    public MiddleAgeMale() {
        super((int) (Math.random() * (5000 - 2500) + 2500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Meat, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Toiletry, (int) (Math.random() * (10 - 6) + 6));
    }
}
