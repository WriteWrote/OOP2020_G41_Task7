package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

public class OldFemale extends Customer {
    public OldFemale(double money) {
        super(money);
        generatePersonalDesires();
    }
    public OldFemale() {
        super((int) (Math.random() * (3500 - 1500) + 1500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Vegetables, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Bread, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Papers, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Clothes, (int) (Math.random() * (10 - 6) + 6));
    }
}
