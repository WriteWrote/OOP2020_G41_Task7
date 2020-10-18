package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Meat, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Toiletry, (int) (Math.random() * (10 - 6) + 6));
    }
}
