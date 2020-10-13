package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

public class YoungMale extends Customer {
    public YoungMale(double money) {
        super(money);
        generatePersonalDesires();
    }

    public YoungMale() {
        super((int) (Math.random() * (1500 - 500) + 500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
