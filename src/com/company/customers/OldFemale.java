package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Vegetables, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Bread, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Papers, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Clothes, (int) (Math.random() * (10 - 6) + 6));
    }
}
