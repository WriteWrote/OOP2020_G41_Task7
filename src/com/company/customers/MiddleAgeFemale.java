package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

public class MiddleAgeFemale extends Customer {
    public MiddleAgeFemale(double money) {
        super(money);
        generatePersonalDesires();
    }

    public MiddleAgeFemale() {
        super((int) (Math.random() * (5000 - 2500) + 2500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Sweeties, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Fish, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Vegetables, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Clothes, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Toiletry, (int) (Math.random() * (10 - 6) + 6));
    }
}
