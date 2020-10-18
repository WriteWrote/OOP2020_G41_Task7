package com.company.products;

public class Countable extends Description {
    private int quantity;

    public Countable(Product name, int price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
