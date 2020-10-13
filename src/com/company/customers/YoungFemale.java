package com.company.customers;

import com.company.Customer;
import com.company.ExampleProduct;

public class YoungFemale extends Customer {
    public YoungFemale(double money) {
        super(money);
        generatePersonalDesires();
    }

    public YoungFemale() {
        super((int) (Math.random() * (1500 - 500) + 500));
        generatePersonalDesires();
    }

    private void generatePersonalDesires() {
        scaleOfDesires.put(ExampleProduct.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Sweeties, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(ExampleProduct.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
