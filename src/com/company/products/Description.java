package com.company.products;

public abstract class Description {
    private Product name;//?
    private int price;
    protected int expirationDays;

    public Description(Product name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(){
        this.price = price;
    }

    public int getExpirationDays() {
        return expirationDays;
    }
    public int getQuantity(){
        return 0;
    }
}
