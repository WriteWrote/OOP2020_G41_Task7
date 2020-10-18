package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Sausage, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Cheese, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Bread, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
