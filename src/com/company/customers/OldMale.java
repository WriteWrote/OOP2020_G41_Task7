package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

public class OldMale extends Customer {
    public OldMale(double money) {
        super(money);
        generatePersonalDesires();
    }
    public OldMale() {
        super((int) (Math.random() * (3500 - 1500) + 1500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Bread, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
