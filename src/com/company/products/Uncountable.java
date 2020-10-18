package com.company.products;

public class Uncountable extends Description {
    private int weight;

    public Uncountable(Product name, int price, int weight) {
        super(name, price);
        this.weight = weight;
    }

    @Override
    public int getQuantity() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
