package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Sweeties, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Fish, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Vegetables, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Clothes, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Toiletry, (int) (Math.random() * (10 - 6) + 6));
    }
}
