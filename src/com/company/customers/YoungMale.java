package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
