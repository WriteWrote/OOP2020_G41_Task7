package com.company.customers;

import com.company.Customer;
import com.company.products.Product;

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
        scaleOfDesires.put(Product.Milk, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Sweeties, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Drink, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Alcohol, (int) (Math.random() * (10 - 6) + 6));
        scaleOfDesires.put(Product.Papers, (int) (Math.random() * (10 - 6) + 6));
    }
}
